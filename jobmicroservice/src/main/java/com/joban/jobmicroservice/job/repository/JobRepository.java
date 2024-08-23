package com.joban.jobmicroservice.job.repository;

import com.joban.jobmicroservice.job.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job,String> {
    List<Job> getAllJobsByCompany(String cmpid);
}
