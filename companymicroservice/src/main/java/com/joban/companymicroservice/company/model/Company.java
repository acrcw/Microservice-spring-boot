package com.joban.companymicroservice.company.model;


import com.joban.companymicroservice.external.Job;
import com.joban.companymicroservice.external.Review;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

//@RequestMapping("/jobs") to define a base url for all the endpoints in the class
// for mongo db we need to use @Document annotation
@Data // autogenerate getters and setters using lombok
@Document(collection = "company")// map this class to collection
public class Company {

    @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String description;
    private List<Job> jobs;
    private List<Review> reviews;
    private double avgRating;
    private int jobCount;


    public Company(){

    }

    public Company(String name, String description) {
        this.description = description;
        this.name = name;
    }

    public Company(String id) {
        this.id = id;
    }
}
