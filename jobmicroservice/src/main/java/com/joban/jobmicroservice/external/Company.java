package com.joban.jobmicroservice.external;

import lombok.Data;
import java.util.List;


@Data // autogenerate getters and setters using lombok
public class Company {


    private String id;
    private String name;
    private String description;
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
