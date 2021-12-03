package com.ftband.nirvana.demorabbit.demorabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@EnableRabbit
@Component
public class RabbitMQListener {

    @RabbitListener(queues = "myQueue1")
    public void processMyQueue1(String message) {
        log.info("The first listener: {}", message);
    }

    @RabbitListener(queues = "myQueue2")
    public void processMyQueue2(String message) {
        log.info("The second listener: {}", message);
    }

    @RabbitListener(queues = "myQueue3")
    public void processMyQueue3(String message) {
        log.info("The third listener: {}", message);
    }

}
