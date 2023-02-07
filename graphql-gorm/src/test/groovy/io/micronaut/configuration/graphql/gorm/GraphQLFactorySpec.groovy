package io.micronaut.configuration.graphql.gorm

import graphql.GraphQL
import graphql.schema.GraphQLCodeRegistry
import graphql.schema.GraphQLSchema
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.grails.gorm.graphql.GraphQLServiceManager
import org.grails.gorm.graphql.Schema
import org.grails.gorm.graphql.binding.GraphQLDataBinder
import org.grails.gorm.graphql.binding.manager.GraphQLDataBinderManager
import org.grails.gorm.graphql.entity.GraphQLEntityNamingConvention
import org.grails.gorm.graphql.entity.property.manager.GraphQLDomainPropertyManager
import org.grails.gorm.graphql.fetcher.manager.GraphQLDataFetcherManager
import org.grails.gorm.graphql.interceptor.manager.GraphQLInterceptorManager
import org.grails.gorm.graphql.response.delete.GraphQLDeleteResponseHandler
import org.grails.gorm.graphql.response.errors.GraphQLErrorsResponseHandler
import org.grails.gorm.graphql.response.pagination.GraphQLPaginationResponseHandler
import org.grails.gorm.graphql.types.DefaultGraphQLTypeManager
import org.grails.gorm.graphql.types.GraphQLTypeManager
import spock.lang.Specification

@MicronautTest
class GraphQLFactorySpec extends Specification {

    @Inject GraphQLCodeRegistry.Builder codeRegistry
    @Inject GraphQLEntityNamingConvention namingConvention
    @Inject GraphQLDomainPropertyManager domainPropertyManager
    @Inject GraphQLPaginationResponseHandler paginationResponseHandler
    @Inject GraphQLDataFetcherManager dataFetcherManager
    @Inject GraphQLDeleteResponseHandler deleteResponseHandler
    @Inject GraphQLInterceptorManager interceptorManager
    @Inject GraphQLServiceManager serviceManager
    @Inject GraphQLTypeManager typeManager
    @Inject GraphQLDataBinder dataBinder
    @Inject GraphQLDataBinderManager dataBinderManager
    @Inject GraphQLErrorsResponseHandler errorsResponseHandler
    @Inject Schema schema
    @Inject GraphQLSchema graphQLSchema
    @Inject GraphQL graphQL

    void "all beans used are generated by micronaut and not local init methods"() {
        expect:
        typeManager.codeRegistry == codeRegistry
        typeManager.namingConvention == namingConvention
        ((DefaultGraphQLTypeManager)typeManager).errorsResponseHandler == errorsResponseHandler
        ((DefaultGraphQLTypeManager)typeManager).propertyManager == domainPropertyManager
        ((DefaultGraphQLTypeManager)typeManager).paginationResponseHandler == paginationResponseHandler

        and:
        dataBinderManager.getDataBinder(GraphQLDataBinder) == dataBinder

        and:
        schema.codeRegistry == codeRegistry
        schema.deleteResponseHandler == deleteResponseHandler
        schema.namingConvention == namingConvention
        schema.typeManager == typeManager
        schema.dataBinderManager == dataBinderManager
        schema.dataFetcherManager == dataFetcherManager
        schema.interceptorManager == interceptorManager
        schema.paginationResponseHandler == paginationResponseHandler
        schema.serviceManager == serviceManager
    }
}