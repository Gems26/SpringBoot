package com.example.consoleservice.consumer;

import com.example.consoleservice.model.Console;
import com.example.consoleservice.repository.ConsoleElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsoleConsumer {

    @Autowired
    private ConsoleElasticRepository repository;

    @KafkaListener(topics = "console-topic", groupId = "console-group")
    public void consume(Console console) {
        repository.save(console);
        System.out.println("Indexed console: " + console.getName());
    }
}
