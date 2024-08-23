package com.joban.reviewmicroservice.review.dto;

import lombok.Data;

@Data
public class ReviewMessageDto {

    private String company;

    public ReviewMessageDto(String company) {
        this.company = company;
    }
}
