/*
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */

/*
 * SodaStore API
 * API for managing soda products and orders in SodaStore.
 *
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.sap.cloud.sdk.datamodel.openapi.sample.model;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * OrderWithTimestamp
 */
// CHECKSTYLE:OFF
public class OrderWithTimestamp
// CHECKSTYLE:ON
{
    @JsonProperty( "productId" )
    private Long productId;

    @JsonProperty( "quantity" )
    private Integer quantity;

    @JsonProperty( "totalPrice" )
    private Float totalPrice;

    @JsonProperty( "typelessProperty" )
    private Object typelessProperty;

    @JsonProperty( "nullableProperty" )
    private String nullableProperty;

    @JsonProperty( "timestamp" )
    private OffsetDateTime timestamp;

    @JsonAnySetter
    @JsonAnyGetter
    private final Map<String, Object> cloudSdkCustomFields = new LinkedHashMap<>();

    /**
     * Default constructor for OrderWithTimestamp.
     */
    protected OrderWithTimestamp()
    {
    }

    /**
     * Set the productId of this {@link OrderWithTimestamp} instance and return the same instance.
     *
     * @param productId
     *            The productId of this {@link OrderWithTimestamp}
     * @return The same instance of this {@link OrderWithTimestamp} class
     */
    @Nonnull
    public OrderWithTimestamp productId( @Nonnull final Long productId )
    {
        this.productId = productId;
        return this;
    }

    /**
     * Get productId
     *
     * @return productId The productId of this {@link OrderWithTimestamp} instance.
     */
    @Nonnull
    public Long getProductId()
    {
        return productId;
    }

    /**
     * Set the productId of this {@link OrderWithTimestamp} instance.
     *
     * @param productId
     *            The productId of this {@link OrderWithTimestamp}
     */
    public void setProductId( @Nonnull final Long productId )
    {
        this.productId = productId;
    }

    /**
     * Set the quantity of this {@link OrderWithTimestamp} instance and return the same instance.
     *
     * @param quantity
     *            The quantity of this {@link OrderWithTimestamp}
     * @return The same instance of this {@link OrderWithTimestamp} class
     */
    @Nonnull
    public OrderWithTimestamp quantity( @Nonnull final Integer quantity )
    {
        this.quantity = quantity;
        return this;
    }

    /**
     * Get quantity
     *
     * @return quantity The quantity of this {@link OrderWithTimestamp} instance.
     */
    @Nonnull
    public Integer getQuantity()
    {
        return quantity;
    }

    /**
     * Set the quantity of this {@link OrderWithTimestamp} instance.
     *
     * @param quantity
     *            The quantity of this {@link OrderWithTimestamp}
     */
    public void setQuantity( @Nonnull final Integer quantity )
    {
        this.quantity = quantity;
    }

    /**
     * Set the totalPrice of this {@link OrderWithTimestamp} instance and return the same instance.
     *
     * @param totalPrice
     *            The totalPrice of this {@link OrderWithTimestamp}
     * @return The same instance of this {@link OrderWithTimestamp} class
     */
    @Nonnull
    public OrderWithTimestamp totalPrice( @Nullable final Float totalPrice )
    {
        this.totalPrice = totalPrice;
        return this;
    }

    /**
     * Get totalPrice
     *
     * @return totalPrice The totalPrice of this {@link OrderWithTimestamp} instance.
     */
    @Nonnull
    public Float getTotalPrice()
    {
        return totalPrice;
    }

    /**
     * Set the totalPrice of this {@link OrderWithTimestamp} instance.
     *
     * @param totalPrice
     *            The totalPrice of this {@link OrderWithTimestamp}
     */
    public void setTotalPrice( @Nullable final Float totalPrice )
    {
        this.totalPrice = totalPrice;
    }

    /**
     * Set the typelessProperty of this {@link OrderWithTimestamp} instance and return the same instance.
     *
     * @param typelessProperty
     *            Some typeless property, interpreted by the generator as nullable by default (because typeless)
     * @return The same instance of this {@link OrderWithTimestamp} class
     */
    @Nonnull
    public OrderWithTimestamp typelessProperty( @Nullable final Object typelessProperty )
    {
        this.typelessProperty = typelessProperty;
        return this;
    }

    /**
     * Some typeless property, interpreted by the generator as nullable by default (because typeless)
     *
     * @return typelessProperty The typelessProperty of this {@link OrderWithTimestamp} instance.
     */
    @Nullable
    public Object getTypelessProperty()
    {
        return typelessProperty;
    }

    /**
     * Set the typelessProperty of this {@link OrderWithTimestamp} instance.
     *
     * @param typelessProperty
     *            Some typeless property, interpreted by the generator as nullable by default (because typeless)
     */
    public void setTypelessProperty( @Nullable final Object typelessProperty )
    {
        this.typelessProperty = typelessProperty;
    }

    /**
     * Set the nullableProperty of this {@link OrderWithTimestamp} instance and return the same instance.
     *
     * @param nullableProperty
     *            Some typed property that is deliberately made nullable
     * @return The same instance of this {@link OrderWithTimestamp} class
     */
    @Nonnull
    public OrderWithTimestamp nullableProperty( @Nullable final String nullableProperty )
    {
        this.nullableProperty = nullableProperty;
        return this;
    }

    /**
     * Some typed property that is deliberately made nullable
     *
     * @return nullableProperty The nullableProperty of this {@link OrderWithTimestamp} instance.
     */
    @Nullable
    public String getNullableProperty()
    {
        return nullableProperty;
    }

    /**
     * Set the nullableProperty of this {@link OrderWithTimestamp} instance.
     *
     * @param nullableProperty
     *            Some typed property that is deliberately made nullable
     */
    public void setNullableProperty( @Nullable final String nullableProperty )
    {
        this.nullableProperty = nullableProperty;
    }

    /**
     * Set the timestamp of this {@link OrderWithTimestamp} instance and return the same instance.
     *
     * @param timestamp
     *            The timestamp of this {@link OrderWithTimestamp}
     * @return The same instance of this {@link OrderWithTimestamp} class
     */
    @Nonnull
    public OrderWithTimestamp timestamp( @Nullable final OffsetDateTime timestamp )
    {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     *
     * @return timestamp The timestamp of this {@link OrderWithTimestamp} instance.
     */
    @Nonnull
    public OffsetDateTime getTimestamp()
    {
        return timestamp;
    }

    /**
     * Set the timestamp of this {@link OrderWithTimestamp} instance.
     *
     * @param timestamp
     *            The timestamp of this {@link OrderWithTimestamp}
     */
    public void setTimestamp( @Nullable final OffsetDateTime timestamp )
    {
        this.timestamp = timestamp;
    }

    /**
     * Get the names of the unrecognizable properties of the {@link OrderWithTimestamp}.
     *
     * @return The set of properties names
     */
    @JsonIgnore
    @Nonnull
    public Set<String> getCustomFieldNames()
    {
        return cloudSdkCustomFields.keySet();
    }

    /**
     * Get the value of an unrecognizable property of this {@link OrderWithTimestamp} instance.
     *
     * @deprecated Use {@link #toMap()} instead.
     * @param name
     *            The name of the property
     * @return The value of the property
     * @throws NoSuchElementException
     *             If no property with the given name could be found.
     */
    @Nullable
    @Deprecated
    public Object getCustomField( @Nonnull final String name )
        throws NoSuchElementException
    {
        if( !cloudSdkCustomFields.containsKey(name) ) {
            throw new NoSuchElementException("OrderWithTimestamp has no field with name '" + name + "'.");
        }
        return cloudSdkCustomFields.get(name);
    }

    /**
     * Get the value of all properties of this {@link OrderWithTimestamp} instance including unrecognized properties.
     *
     * @return The map of all properties
     */
    @JsonIgnore
    @Nonnull
    public Map<String, Object> toMap()
    {
        final Map<String, Object> declaredFields = new LinkedHashMap<>(cloudSdkCustomFields);
        if( productId != null )
            declaredFields.put("productId", productId);
        if( quantity != null )
            declaredFields.put("quantity", quantity);
        if( totalPrice != null )
            declaredFields.put("totalPrice", totalPrice);
        if( typelessProperty != null )
            declaredFields.put("typelessProperty", typelessProperty);
        if( nullableProperty != null )
            declaredFields.put("nullableProperty", nullableProperty);
        if( timestamp != null )
            declaredFields.put("timestamp", timestamp);
        return declaredFields;
    }

    /**
     * Set an unrecognizable property of this {@link OrderWithTimestamp} instance. If the map previously contained a
     * mapping for the key, the old value is replaced by the specified value.
     *
     * @param customFieldName
     *            The name of the property
     * @param customFieldValue
     *            The value of the property
     */
    @JsonIgnore
    public void setCustomField( @Nonnull String customFieldName, @Nullable Object customFieldValue )
    {
        cloudSdkCustomFields.put(customFieldName, customFieldValue);
    }

    @Override
    public boolean equals( @Nullable final java.lang.Object o )
    {
        if( this == o ) {
            return true;
        }
        if( o == null || getClass() != o.getClass() ) {
            return false;
        }
        final OrderWithTimestamp orderWithTimestamp = (OrderWithTimestamp) o;
        return Objects.equals(this.cloudSdkCustomFields, orderWithTimestamp.cloudSdkCustomFields)
            && Objects.equals(this.productId, orderWithTimestamp.productId)
            && Objects.equals(this.quantity, orderWithTimestamp.quantity)
            && Objects.equals(this.totalPrice, orderWithTimestamp.totalPrice)
            && Objects.equals(this.typelessProperty, orderWithTimestamp.typelessProperty)
            && Objects.equals(this.nullableProperty, orderWithTimestamp.nullableProperty)
            && Objects.equals(this.timestamp, orderWithTimestamp.timestamp);
    }

    @Override
    public int hashCode()
    {
        return Objects
            .hash(productId, quantity, totalPrice, typelessProperty, nullableProperty, timestamp, cloudSdkCustomFields);
    }

    @Override
    @Nonnull
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("class OrderWithTimestamp {\n");
        sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
        sb.append("    typelessProperty: ").append(toIndentedString(typelessProperty)).append("\n");
        sb.append("    nullableProperty: ").append(toIndentedString(nullableProperty)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        cloudSdkCustomFields
            .forEach(( k, v ) -> sb.append("    ").append(k).append(": ").append(toIndentedString(v)).append("\n"));
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString( final java.lang.Object o )
    {
        if( o == null ) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    /**
     * Create a type-safe, fluent-api builder object to construct a new {@link OrderWithTimestamp} instance with all
     * required arguments.
     */
    public static Builder create()
    {
        return ( productId ) -> ( quantity ) -> new OrderWithTimestamp().productId(productId).quantity(quantity);
    }

    /**
     * Builder helper class.
     */
    public interface Builder
    {
        /**
         * Set the productId of this {@link OrderWithTimestamp} instance.
         *
         * @param productId
         *            The productId of this {@link OrderWithTimestamp}
         * @return The OrderWithTimestamp builder.
         */
        Builder1 productId( @Nonnull final Long productId );
    }

    /**
     * Builder helper class.
     */
    public interface Builder1
    {
        /**
         * Set the quantity of this {@link OrderWithTimestamp} instance.
         *
         * @param quantity
         *            The quantity of this {@link OrderWithTimestamp}
         * @return The OrderWithTimestamp instance.
         */
        OrderWithTimestamp quantity( @Nonnull final Integer quantity );
    }

}
