package com.joban.jobmicroservice.job.messaging;

import com.joban.jobmicroservice.job.dto.JobMessageDto;
import com.joban.jobmicroservice.job.model.Job;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public JobMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Job j){
        JobMessageDto msg = new JobMessageDto(j.getCompany());
        rabbitTemplate.convertAndSend("jobCountQueue",msg);
    }
}
