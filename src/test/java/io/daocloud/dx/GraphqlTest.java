package io.daocloud.dx;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

/**
 * @Author: Devin
 * @Date: 2020-03-27 13:02
 * @Version 1.0
 */
public class GraphqlTest {

    public static void main(String[] args) {
        String schema = "type Query{hello: String}";
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring().type("query" ,builder -> builder.dataFetcher("hello",new StaticDataFetcher("world"))).build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        GraphQL build= GraphQL.newGraphQL(graphQLSchema).build();

        ExecutionResult execute =
                build.execute("{hello}");
        System.out.println(execute.getData().toString());
    }
}
