package com.gentaliti.elasticsearch.repository;

import com.gentaliti.elasticsearch.document.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Account repository
 *
 * @author Gent Aliti <alitigenti@gmail.com/>
 */
@Repository
public interface AccountRepository extends ElasticsearchRepository<Account, String> {
}
