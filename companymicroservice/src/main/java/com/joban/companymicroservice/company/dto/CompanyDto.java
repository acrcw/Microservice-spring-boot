package com.joban.companymicroservice.company.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
@Data // autogenerate getters and setters using lombok
@Document
public class CompanyDto {
    private String name;
    private String description;

}
