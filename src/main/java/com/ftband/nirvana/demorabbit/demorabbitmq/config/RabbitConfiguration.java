package com.ftband.nirvana.demorabbit.demorabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitConfiguration {

    @Bean
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }
    
    @Bean
    RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        return rabbitTemplate;
    }

    @Bean
    Queue myQueue1() {
        return new Queue("myQueue1");
    }

    @Bean
    Queue myQueue2() {
        return new Queue("myQueue2");
    }

    @Bean
    Queue myQueue3() {
        return new Queue("myQueue3");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout exchange");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("direct exchange");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic exchange");
    }

    @Bean
    Binding bindingDirect1() {
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("1");
    }

    @Bean
    Binding bindingDirect2() {
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("2");
    }

    @Bean
    Binding bindingDirect3() {
        return BindingBuilder.bind(myQueue3()).to(directExchange()).with("3");
    }

    @Bean
    Binding bindingFanout1() {
        return BindingBuilder.bind(myQueue1()).to(fanoutExchange());
    }

    @Bean
    Binding bindingFanout2() {
        return BindingBuilder.bind(myQueue2()).to(fanoutExchange());
    }

    @Bean
    Binding bindingTopic1() {
        return BindingBuilder.bind(myQueue1()).to(topicExchange()).with("*.one");
    }

    @Bean
    Binding bindingTopic2() {
        return BindingBuilder.bind(myQueue2()).to(topicExchange()).with("*.two");
    }

    @Bean
    Binding bindingTopic3() {
        return BindingBuilder.bind(myQueue3()).to(topicExchange()).with("*.three");
    }

    /*@Bean
    SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("myQueue");
        container.setMessageListener(message -> System.out.println(new String(message.getBody())));
        return container;
    }*/
}
