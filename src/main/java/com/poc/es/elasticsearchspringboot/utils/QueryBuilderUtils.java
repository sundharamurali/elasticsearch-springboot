package com.poc.es.elasticsearchspringboot.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;

public interface QueryBuilderUtils {

    public static Query termQuery(String field, String value){
        QueryVariant queryVariant = new TermQuery.Builder().caseInsensitive(true).field(field).value(value).build();
        return new Query(queryVariant);
    }
}
