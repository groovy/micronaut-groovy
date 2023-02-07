/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.multitenancy.gorm;

import io.micronaut.multitenancy.tenantresolver.SystemPropertyTenantResolverConfigurationProperties;
import org.grails.datastore.mapping.multitenancy.TenantResolver;
import org.grails.datastore.mapping.multitenancy.exceptions.TenantNotFoundException;

import java.io.Serializable;

/**
 * A tenant resolver that resolves the tenant from the request HTTP Header.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public class SystemPropertyTenantResolver implements TenantResolver {

    protected final TenantResolver delegate = new TenantResolverAdapter(new io.micronaut.multitenancy.tenantresolver.SystemPropertyTenantResolver(new SystemPropertyTenantResolverConfigurationProperties()));

    /**
     * Constructs a System property Tenant Resolver.
     */
    public SystemPropertyTenantResolver() {

    }

    @Override
    public Serializable resolveTenantIdentifier() throws TenantNotFoundException {
        return delegate.resolveTenantIdentifier();
    }
}