/*
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */

package testcomparison.namespaces.entitywithkeynamedfield;

import javax.annotation.Nonnull;
import com.sap.cloud.sdk.datamodel.odata.helper.FluentHelperUpdate;


/**
 * Fluent helper to update an existing {@link testcomparison.namespaces.entitywithkeynamedfield.SomeTypeLabel SomeTypeLabel} entity and save it to the S/4HANA system.<p>
 * To perform execution, call the {@link #executeRequest executeRequest} method on the fluent helper object.
 * 
 */
public class SomeTypeLabelUpdateFluentHelper
    extends FluentHelperUpdate<SomeTypeLabelUpdateFluentHelper, SomeTypeLabel>
{

    /**
     * {@link testcomparison.namespaces.entitywithkeynamedfield.SomeTypeLabel SomeTypeLabel} entity object that will be updated in the S/4HANA system.
     * 
     */
    private final SomeTypeLabel entity;

    /**
     * Creates a fluent helper object that will update a {@link testcomparison.namespaces.entitywithkeynamedfield.SomeTypeLabel SomeTypeLabel} entity on the OData endpoint. To perform execution, call the {@link #executeRequest executeRequest} method on the fluent helper object.
     * 
     * @param servicePath
     *     The service path to direct the update requests to.
     * @param entity
     *     The SomeTypeLabel to take the updated values from.
     */
    public SomeTypeLabelUpdateFluentHelper(
        @Nonnull
        final String servicePath,
        @Nonnull
        final SomeTypeLabel entity,
        @Nonnull
        final String entityCollection) {
        super(servicePath, entityCollection);
        this.entity = entity;
    }

    @Override
    @Nonnull
    protected SomeTypeLabel getEntity() {
        return entity;
    }

}
