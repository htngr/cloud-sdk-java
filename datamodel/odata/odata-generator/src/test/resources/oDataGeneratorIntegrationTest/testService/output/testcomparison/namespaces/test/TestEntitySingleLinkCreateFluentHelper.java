/*
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */

package testcomparison.namespaces.test;

import javax.annotation.Nonnull;
import com.sap.cloud.sdk.datamodel.odata.helper.FluentHelperCreate;


/**
 * Fluent helper to create a new {@link testcomparison.namespaces.test.TestEntitySingleLink TestEntitySingleLink} entity and save it to the S/4HANA system.<p>
 * To perform execution, call the {@link #executeRequest executeRequest} method on the fluent helper object.
 * 
 */
public class TestEntitySingleLinkCreateFluentHelper
    extends FluentHelperCreate<TestEntitySingleLinkCreateFluentHelper, TestEntitySingleLink>
{

    /**
     * {@link testcomparison.namespaces.test.TestEntitySingleLink TestEntitySingleLink} entity object that will be created in the S/4HANA system.
     * 
     */
    private final TestEntitySingleLink entity;

    /**
     * Creates a fluent helper object that will create a {@link testcomparison.namespaces.test.TestEntitySingleLink TestEntitySingleLink} entity on the OData endpoint. To perform execution, call the {@link #executeRequest executeRequest} method on the fluent helper object.
     * 
     * @param entityCollection
     *     Entity Collection  to direct the create requests to.
     * @param servicePath
     *     The service path to direct the create requests to.
     * @param entity
     *     The TestEntitySingleLink to create.
     */
    public TestEntitySingleLinkCreateFluentHelper(
        @Nonnull
        final String servicePath,
        @Nonnull
        final TestEntitySingleLink entity,
        @Nonnull
        final String entityCollection) {
        super(servicePath, entityCollection);
        this.entity = entity;
    }

    @Override
    @Nonnull
    protected TestEntitySingleLink getEntity() {
        return entity;
    }

}
