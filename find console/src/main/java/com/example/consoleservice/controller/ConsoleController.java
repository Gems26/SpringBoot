package com.example.consoleservice.controller;

import com.example.consoleservice.model.Console;
import com.example.consoleservice.repository.ConsoleElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consoles")
public class ConsoleController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ConsoleElasticRepository repository;

    private static final String TOPIC = "console-topic";

    @PostMapping
    public String addConsole(@RequestBody Console console) {
        kafkaTemplate.send(TOPIC, console);
        return "Console published to Kafka topic.";
    }

    @GetMapping("/search")
    public List<Console> search(@RequestParam String q) {
        return repository.findByNameContaining(q);
    }
}
