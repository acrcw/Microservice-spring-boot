package com.joban.jobmicroservice.job.dto;

import lombok.Data;

@Data
public class JobMessageDto {
    private String company;

    public JobMessageDto(String company) {
        this.company = company;
    }
}
