package com.joban.jobmicroservice.job.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue companyRatingQueue(){
        // create a new queue
        return new Queue("jobCountQueue");
    }
    @Bean
    public MessageConverter jsonMessageConvertor(){
        // message convertor using jackson from json to java
        // to serialize and deserialize messages
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        // helper class handle creation and release of resources
        RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConvertor());
        return rabbitTemplate;
        }
}
