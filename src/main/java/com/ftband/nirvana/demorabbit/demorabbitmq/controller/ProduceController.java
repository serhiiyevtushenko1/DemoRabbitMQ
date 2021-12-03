package com.ftband.nirvana.demorabbit.demorabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProduceController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProduceController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/direct")
    public ResponseEntity<String> directController(@RequestBody Map<String, String> map) {
        rabbitTemplate.setExchange("direct exchange");
        rabbitTemplate.convertAndSend(map.get("key"), map.get("message"));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/fanout")
    public ResponseEntity<String> fanoutController(@RequestBody String message) {
        rabbitTemplate.setExchange("fanout exchange");
        rabbitTemplate.convertAndSend(message);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/topic")
    public ResponseEntity<String> topicController(@RequestBody Map<String, String> map) {
        rabbitTemplate.setExchange("topic exchange");
        rabbitTemplate.convertAndSend(map.get("key"), map.get("message"));
        return ResponseEntity.ok("Success");
    }
}
