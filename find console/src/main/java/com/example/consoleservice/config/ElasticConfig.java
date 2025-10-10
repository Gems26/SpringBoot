package com.example.consoleservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.context.annotation.Bean;

@Configuration
public class ElasticConfig {

    @Bean(name = {"elasticsearchTemplate", "elasticsearchRestTemplate"})
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return new ElasticsearchRestTemplate(RestClients.create(clientConfiguration).rest());
    }
}
