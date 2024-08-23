package com.joban.companymicroservice.company.messaging;

import com.joban.companymicroservice.company.Service.CompanyService;
import com.joban.companymicroservice.company.dto.JobMessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class JobMessageConsumer {
    private final CompanyService svc;

    public JobMessageConsumer(CompanyService svc) {
        this.svc = svc;
    }
    @RabbitListener(queues = "jobCountQueue")
    public void consumeMessage(JobMessageDto msg){
        System.out.println(msg.getCompany());
        svc.updateCompanyJobCount(msg);
    }
}
