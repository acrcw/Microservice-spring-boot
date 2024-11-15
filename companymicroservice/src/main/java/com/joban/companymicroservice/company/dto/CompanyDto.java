package com.joban.companymicroservice.company.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
@Data // autogenerate getters and setters using lombok
@Document
public class CompanyDto {
    @NotEmpty
    @Size(min = 10, message = "Company name should have at least 10 characters")
    private String name;
    @NotEmpty(message = "Must not be Empty and NULL")
    private String description;

}
