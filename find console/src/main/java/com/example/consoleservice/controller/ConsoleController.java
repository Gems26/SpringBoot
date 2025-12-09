package com.example.consoleservice.controller;

import com.example.consoleservice.model.Console;
import com.example.consoleservice.repository.ConsoleElasticRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.List;

@RestController
@RequestMapping("/api/consoles")
public class ConsoleController {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ConsoleElasticRepository repository;
    private final ElasticsearchOperations elasticsearchOperations;

    private static final String TOPIC = "console-topic";

    // Constructor injection (preferred for Spring Boot 3.x)
    public ConsoleController(KafkaTemplate<String, Object> kafkaTemplate,
                             ConsoleElasticRepository repository,
                             ElasticsearchOperations elasticsearchOperations) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @PostMapping
    public String addConsole(@RequestBody Console console) {
        // Publish to Kafka
        kafkaTemplate.send(TOPIC, console);
        // Optional: save directly to Elasticsearch
        repository.save(console);
        return "✅ Console published to Kafka topic and saved to Elasticsearch: " + TOPIC;
    }

    @GetMapping("/search")
    public List<Console> search(@RequestParam String q) {
        // Case-insensitive search in Elasticsearch
        return repository.findByNameContainingIgnoreCase(q);
    }
}
