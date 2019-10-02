package com.gentaliti.elasticsearch.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Elasticsearch configuruation
 *
 * @author Gent Aliti <alitigenti@gmail.com/>
 */
@Configuration
@EnableElasticsearchRepositories
public class ElasticsearchConfig {
    @Value("${elasticsearch.cluster-name}")
    private String clusterName;

    @Value("${elasticsearch.port}")
    private int port;

    @Value("${elasticsearch.host}")
    private String host;

    @Bean
    public Client client() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", clusterName).put("network.host", host).build();
        return new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }
}
