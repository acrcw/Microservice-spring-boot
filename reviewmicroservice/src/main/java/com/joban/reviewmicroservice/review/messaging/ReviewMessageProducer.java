package com.joban.reviewmicroservice.review.messaging;

import com.joban.reviewmicroservice.review.dto.ReviewMessageDto;
import com.joban.reviewmicroservice.review.model.Review;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review r){
        ReviewMessageDto msg = new ReviewMessageDto(r.getCompany());
        rabbitTemplate.convertAndSend("companyRatingQueue",msg);
    }
}
