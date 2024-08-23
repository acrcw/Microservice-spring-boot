package com.joban.companymicroservice.company.repository;

import com.joban.companymicroservice.company.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company,String> {
}
