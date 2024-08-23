package com.joban.reviewmicroservice.review.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data // autogenerate getters and setters using lombok
//@RequestMapping("/jobs") to define a base url for all the endpoints in the class
// for mongo db we need to use @Document annotation
@Document(collection = "reviews")// map this class to collection
public class Review {

    @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private String title;
    private String description;
    private double rating;

    @JsonIgnore
    private String company;


    public Review(String title, String description, double rating, String company) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.company = company;
    }

    public Review(){
    }

    public Review(String title, String description, double rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }
}
