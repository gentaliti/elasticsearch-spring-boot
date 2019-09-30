package com.gentaliti.elasticsearch.controller;

import com.gentaliti.elasticsearch.dto.Filter;
import com.gentaliti.elasticsearch.service.AccountSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController("/accounts")
public class SearchController {
    private AccountSearchService accountSearchService;

    @Autowired
    public SearchController(AccountSearchService accountSearchService) {
        this.accountSearchService = accountSearchService;
    }

    @GetMapping
    public Page searchObjects(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "filter", required = false) String filter,
            @PageableDefault(value = 10, page = 0) Pageable pageable) {
        return this.accountSearchService.search(query, this.map(filter), pageable);
    }

    private List<Filter> map(String filters) {
        List<Filter> filterList = new ArrayList<>();
        if (filters == null) {
            return filterList;
        }
        if (filters.contains(";")) {
            for (String filter : filters.split(";")) {
                filterList.addAll(this.mapValues(filter));
            }
        } else {
            filterList.addAll(this.mapValues(filters));
        }
        return filterList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private List<Filter> mapValues(String filter) {
        List<Filter> filters = new ArrayList<>();
        if (!filter.contains(":")) {
            return filters;
        }

        final String[] values = filter.split(":");
        if (values[1].contains(",")) {
            for (String f : values[1].split(",")) {
                filters.add(new Filter(values[0], f));
            }
        } else {
            filters.add(new Filter(values[0], values[1]));
        }

        return filters;
    }
}
