package com.example.consoleservice.repository;

import com.example.consoleservice.model.Console;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsoleElasticRepository extends ElasticsearchRepository<Console, String> {
    List<Console> findByNameContaining(String name);
}
