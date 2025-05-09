package com.sap.cloud.sdk.cloudplatform.connectivity;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.sap.cloud.environment.servicebinding.api.TypedMapView;
import com.sap.cloud.sdk.cloudplatform.connectivity.SecurityLibWorkarounds.ZtisClientIdentity;
import com.sap.cloud.sdk.cloudplatform.connectivity.exception.DestinationAccessException;
import com.sap.cloud.sdk.cloudplatform.exception.CloudPlatformException;
import com.sap.cloud.security.config.ClientCertificate;
import com.sap.cloud.security.config.ClientCredentials;
import com.sap.cloud.security.config.ClientIdentity;
import com.sap.cloud.security.config.CredentialType;

import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

/**
 * A default strategy to extract OAuth client information from service bindings.
 * <p>
 * Supports bindings with a {@code credentials} block containing a {@code clientid}. Further OAuth properties are
 * expected to also be contained in the {@code credentials} block.
 *
 * @since 4.20.0
 */
@Slf4j
public class DefaultOAuth2PropertySupplier implements OAuth2PropertySupplier
{
    /**
     * By default, the oauth client details are expected to be contained in a {@code uaa} block of the
     * {@code credentials} of the service binding.
     */
    @Nonnull
    public static final List<String> DEFAULT_UAA_CREDENTIAL_PATH = Collections.singletonList("uaa");

    /**
     * The {@link ServiceBindingDestinationOptions} containing the service binding to interpret.
     */
    @Nonnull
    protected final ServiceBindingDestinationOptions options;
    /**
     * The credentials contained in the service binding.
     */
    @Nonnull
    protected final TypedMapView credentials;
    @Nonnull
    private final List<String> oauthPropertyPath;

    /**
     * Create a new instance to parse the given service binding. Will use {@link #DEFAULT_UAA_CREDENTIAL_PATH} as the
     * default path to oauth properties.
     *
     * @param options
     *            The options containing the service binding to interpret.
     * @see #DefaultOAuth2PropertySupplier(ServiceBindingDestinationOptions, List)
     */
    public DefaultOAuth2PropertySupplier( @Nonnull final ServiceBindingDestinationOptions options )
    {
        this(options, DEFAULT_UAA_CREDENTIAL_PATH);
    }

    /**
     * Create a new instance to parse the given service binding. Will use the given list as the path to url property.
     *
     * @param options
     *            The options containing the service binding to interpret.
     * @param oauthPropertyPath
     *            The path to the oauth properties in the service binding.
     * @see #DefaultOAuth2PropertySupplier(ServiceBindingDestinationOptions)
     */
    public DefaultOAuth2PropertySupplier(
        @Nonnull final ServiceBindingDestinationOptions options,
        @Nonnull final List<String> oauthPropertyPath )
    {
        this.options = options;
        this.oauthPropertyPath = oauthPropertyPath;
        credentials = TypedMapView.ofCredentials(options.getServiceBinding());
    }

    @Override
    public boolean isOAuth2Binding()
    {
        return getOAuthCredential(String.class, "clientid").isDefined();
    }

    @Override
    @Nonnull
    public URI getServiceUri()
    {
        return getCredentialOrThrow(URI.class, "url");
    }

    @Override
    @Nonnull
    public URI getTokenUri()
    {
        final String tokenUrlProperty = switch( getCredentialType() ) {
            // note: this is for XSUAA only and does not apply for IAS (check the overridden method in the property supplier for IAS)
            // currently XSUAA only recognizes X509 and X509_ATTESTED, so we don't necessarily have to list X509_PROVIDED and X509_GENERATED here
            // still, doesn't hurt and is more future-proof in case XSUAA ever starts adopting these identifiers
            case X509, X509_GENERATED, X509_PROVIDED, X509_ATTESTED -> "certurl";
            case BINDING_SECRET, INSTANCE_SECRET -> "url";
        };
        return getOAuthCredentialOrThrow(URI.class, tokenUrlProperty);
    }

    @Override
    @Nonnull
    public ClientIdentity getClientIdentity()
    {
        final String clientid = getOAuthCredentialOrThrow(String.class, "clientid");

        return switch( getCredentialType() ) {
            case BINDING_SECRET, INSTANCE_SECRET -> getSecretIdentity(clientid);
            case X509, X509_GENERATED -> getCertificateIdentity(clientid);
            case X509_ATTESTED -> getZtisIdentity(clientid);
            case X509_PROVIDED -> throw new DestinationAccessException(
                "Credential type X509_PROVIDED is not supported. Please use X509_GENERATED or X509_ATTESTED instead.");
        };
    }

    @Nonnull
    @Override
    public OAuth2Options getOAuth2Options()
    {
        final OAuth2Options.Builder builder = OAuth2Options.builder();
        options.getOption(OAuth2Options.TokenRetrievalTimeout.class).peek(builder::withTimeLimiter);
        return builder.build();
    }

    /**
     * Get the path under which the oauth properties are stored in the service binding credentials.
     *
     * @return The path to the oauth properties.
     */
    @Nonnull
    protected List<String> getOAuthPropertyPath()
    {
        return new ArrayList<>(oauthPropertyPath);
    }

    @Nonnull
    private ClientIdentity getCertificateIdentity( @Nonnull final String clientid )
    {
        final String cert = getOAuthCredentialOrThrow(String.class, "certificate");
        final String key = getOAuthCredentialOrThrow(String.class, "key");
        return new ClientCertificate(cert, key, clientid);
    }

    @Nonnull
    private ZtisClientIdentity getZtisIdentity( @Nonnull final String clientid )
    {
        try {
            // sanity check: assert the connectivity-ztis module is present
            getClass()
                .getClassLoader()
                .loadClass("com.sap.cloud.sdk.cloudplatform.connectivity.ZeroTrustIdentityService");
        }
        catch( final ClassNotFoundException e ) {
            throw new CloudPlatformException(
                "Failed to load implementation for credential type X509_ATTESTED. Please ensure the 'connectivity-ztis' module is present.",
                e);
        }
        final ZeroTrustIdentityService ztis = ZeroTrustIdentityService.getInstance();

        final KeyStore keyStore;
        try {
            keyStore = ztis.getOrCreateKeyStore();
        }
        catch( final Exception e ) {
            throw new CloudPlatformException("Failed to load X509 certificate for credential type X509_ATTESTED.", e);
        }
        return new ZtisClientIdentity(clientid, keyStore);
    }

    @Nonnull
    private ClientIdentity getSecretIdentity( @Nonnull final String clientid )
    {
        final String secret = getOAuthCredentialOrThrow(String.class, "clientsecret");
        return new ClientCredentials(clientid, secret);
    }

    @Nonnull
    CredentialType getCredentialType()
    {
        return getOAuthCredential(CredentialType.class, "credential-type")
            .onEmpty(
                () -> log
                    .warn(
                        "Credential type not found or not recognised in service binding. Defaulting to BINDING_SECRET."))
            .getOrElse(CredentialType.BINDING_SECRET);
    }

    /**
     * Obtain an entry from the credentials block of the service binding.
     *
     * @param <T>
     *            The type of the entry to obtain.
     * @param resultType
     *            The type of the entry to obtain.
     * @param path
     *            The path to the entry to obtain.
     * @return The entry from the credentials block of the service binding.
     * @throws DestinationAccessException
     *             If the property was not found or could not be converted to the requested type.
     * @see #getCredential(Class, String...) for a variant that doesn't throw
     */
    @Nonnull
    protected <T> T getCredentialOrThrow( @Nonnull final Class<T> resultType, @Nonnull final String... path )
        throws DestinationAccessException
    {
        return getCredential(resultType, path)
            .getOrElseThrow(
                () -> new DestinationAccessException(
                    "Failed to resolve property " + Arrays.toString(path) + " from service binding."));
    }

    /**
     * Obtain an entry from the credentials block of the service binding.
     *
     * @param <T>
     *            The type of the entry to obtain.
     * @param resultType
     *            The type of the entry to obtain.
     * @param path
     *            The path to the entry to obtain.
     * @return The entry from the credentials block of the service binding.
     * @see #getCredentialOrThrow(Class, String...)
     */
    @Nonnull
    protected <T> Option<T> getCredential( @Nonnull final Class<T> resultType, @Nonnull final String... path )
    {
        return getCredentialInternal(resultType, credentials, Arrays.asList(path));
    }

    /**
     * Obtain an oauth property from the credentials block of the service binding. Uses {@link #getOAuthPropertyPath()}
     * as prefix to the path.
     *
     * @param <T>
     *            The type of the entry to obtain.
     * @param resultType
     *            The type of the entry to obtain.
     * @param path
     *            The path to the entry to obtain.
     * @return The oauth property from the credentials block of the service binding.
     * @throws DestinationAccessException
     *             If the property was not found or could not be converted to the requested type.
     * @see #getOAuthCredential(Class, String...) (Class, String...) for a variant that doesn't throw
     */
    @Nonnull
    protected <T> T getOAuthCredentialOrThrow( @Nonnull final Class<T> resultType, @Nonnull final String... path )
        throws DestinationAccessException
    {
        return getOAuthCredential(resultType, path)
            .getOrElseThrow(
                () -> new DestinationAccessException(
                    "Failed to resolve property "
                        + getOAuthPropertyPath()
                        + Arrays.toString(path)
                        + " from service binding."));
    }

    /**
     * Obtain an oauth property from the credentials block of the service binding. Uses {@link #getOAuthPropertyPath()}
     * as prefix to the path.
     *
     * @param <T>
     *            The type of the entry to obtain.
     * @param resultType
     *            The type of the entry to obtain.
     * @param path
     *            The path to the entry to obtain.
     * @return The oauth property from the credentials block of the service binding.
     * @see #getOAuthCredential(Class, String...)
     */
    @Nonnull
    protected <T> Option<T> getOAuthCredential( @Nonnull final Class<T> resultType, @Nonnull final String... path )
    {
        final List<String> fullPath = getOAuthPropertyPath();
        fullPath.addAll(Arrays.asList(path));
        return getCredentialInternal(resultType, credentials, fullPath);
    }

    @Nonnull
    private <T> Option<T> getCredentialInternal(
        @Nonnull final Class<T> resultType,
        @Nonnull final TypedMapView currentNode,
        @Nonnull final List<String> names )
    {
        if( names.isEmpty() ) {
            log.warn("Passed an empty property path to load from the service binding. This should never happen.");
            return Option.none();
        }
        if( names.size() == 1 ) {
            return Try.of(() -> currentNode.get(names.get(0))).map(o -> convert(o, resultType)).toOption();
        }
        return Try
            .of(() -> currentNode.getMapView(names.get(0)))
            .toOption()
            .flatMap(it -> getCredentialInternal(resultType, it, names.subList(1, names.size())));
    }

    @SuppressWarnings( "unchecked" )
    @Nullable
    static <T> T convert( @Nullable final Object value, @Nonnull final Class<T> cls )
        throws DestinationAccessException
    {
        if( value == null ) {
            return null;
        }
        if( cls.isAssignableFrom(value.getClass()) ) {
            return (T) value;
        }
        if( cls == String.class ) {
            return (T) value.toString();
        }
        if( cls == URI.class ) {
            try {
                return (T) new URI(value.toString());
            }
            catch( final ClassCastException | URISyntaxException e ) {
                throw new DestinationAccessException("Unable to convert '" + value + "' into an URI.", e);
            }
        }
        if( cls == Integer.class ) {
            try {
                return (T) Integer.valueOf((String) value);
            }
            catch( final ClassCastException | NumberFormatException e ) {
                throw new DestinationAccessException("Unable to convert '" + value + "' into an Integer.", e);
            }
        }
        if( cls == CredentialType.class ) {
            try {
                final T result = (T) CredentialType.from((String) value);
                if( result == null ) {
                    throw new IllegalArgumentException();
                }
                return result;
            }
            catch( final ClassCastException | IllegalArgumentException e ) {
                throw new DestinationAccessException("Unable to convert '" + value + "' into a CredentialType.", e);
            }
        }
        throw new DestinationAccessException(
            "Property value " + value + " could not be parsed into unknown type " + cls.getSimpleName());
    }
}
