package com.joban.companymicroservice.company.messaging;

import com.joban.companymicroservice.company.Service.CompanyService;
import com.joban.companymicroservice.company.dto.ReviewMessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private final CompanyService svc;

    public ReviewMessageConsumer(CompanyService svc) {
        this.svc = svc;
    }
    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessageDto msg){
        svc.updateCompanyRating(msg);
    }
}
