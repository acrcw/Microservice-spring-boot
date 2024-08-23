package com.joban.jobmicroservice.job.repository;

import com.joban.jobmicroservice.job.model.Job;

import java.util.List;

public interface JobSearch {
    List<Job> searchByTerm(String term);
}
