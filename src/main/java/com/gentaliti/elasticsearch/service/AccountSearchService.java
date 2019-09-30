package com.gentaliti.elasticsearch.service;

import com.gentaliti.elasticsearch.repository.AccountRepository;
import com.gentaliti.elasticsearch.dto.Filter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountSearchService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountSearchService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Page search(String query, List<Filter> filters, Pageable pageable) {
        BoolQueryBuilder searchQuery = QueryBuilders.boolQuery();
        if (query != null && !query.isEmpty()) {
            MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(query);
            searchQuery.must(multiMatchQuery);
        }
        prepareFilters(searchQuery, filters);
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(searchQuery).withPageable(pageable).withIndices("bank").withTypes("account");
        return accountRepository.search(queryBuilder.build());
    }

    private void prepareFilters(BoolQueryBuilder searchQuery, List<Filter> filters) {
        if (filters == null) {
            return;
        }
        filters.stream().collect(Collectors.groupingBy(Filter::getKey)).forEach((key, values) -> {
            BoolQueryBuilder bool = QueryBuilders.boolQuery();
            values.forEach(value -> bool.should(QueryBuilders.matchQuery(key, value.getValue())));
            searchQuery.must(bool);
        });
    }
}
