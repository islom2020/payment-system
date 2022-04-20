package com.example.paymentsysteminjava.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitMq.topic.exchange.name}")
    private String exchangeName;

    @Value("${rabbitMq.topic.queue.name}")
    private String queueName;

    @Value("${rabbitMq.topic.route.key.name}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(this.queueName);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(this.exchangeName);
    }

    @Bean
    public Binding bind() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(this.routingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
