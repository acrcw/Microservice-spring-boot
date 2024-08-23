package com.joban.reviewmicroservice.review.dto;


import lombok.Data;

@Data // autogenerate getters and setters using lombok
public class ReviewDto {
    private String title;
    private String description;
    private double rating;



    public ReviewDto(String title, String description, double rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;

    }
}
