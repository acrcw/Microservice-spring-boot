package com.joban.jobmicroservice.job.model;

//import com.joban.JobSeeker.company.model.Company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data // autogenerate getters and setters using lombok
//@RequestMapping("/jobs") to define a base url for all the endpoints in the class
// for mongo db we need to use @Document annotation
@Document(collection = "jobs")// map this class to collection
public class Job {

    @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private String jobType;
    private String [] skills;
    private int exp;
    @JsonIgnore
    private String company;


    public Job(String title, String description, String minSalary, String maxSalary, String location, String jobType, String[] skills, int exp, String company) {
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.jobType = jobType;
        this.skills = skills;
        this.exp = exp;
        this.company = company;
    }

    public Job(String title, String description, String minSalary, String maxSalary, String location, String jobType, String[] skills, int exp) {
        this.description = description;
        this.title = title;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.jobType = jobType;
        this.skills = skills;
        this.exp = exp;
    }

    public Job(){

    }
}
