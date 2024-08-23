package com.joban.jobmicroservice.job.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data // autogenerate getters and setters using lombok
public class JobDto {
    @NotNull
    @NotEmpty(message = "Title is required.")
    private String title;

    @NotNull
    @NotEmpty(message = "Description is required.")
    private String description;

    @NotNull
    @NotEmpty(message = "minSalary is required.")
    private String minSalary;

    @NotNull
    @NotEmpty(message = "maxSalary is required.")
    private String maxSalary;

    @NotNull
    @NotEmpty(message = "Title is required.")
    private String location;

    @NotNull
    @NotEmpty(message = "Title is required.")
    private String jobType;


    @NotNull(message = "The skills are required.")
    private String [] skills;

    private int exp;

//    @DocumentReference(lazy = true)
//    private CompanyDto company;




    public JobDto(String title, String description, String minSalary, String maxSalary, String location, String jobType, @NotNull(message = "The skills are required.") String[] skills, int exp) {
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.jobType = jobType;
        this.skills = skills;
        this.exp = exp;
    }

}
