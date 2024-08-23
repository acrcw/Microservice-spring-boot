package com.joban.jobmicroservice.job.Service;

import com.joban.jobmicroservice.job.model.Job;
import com.joban.jobmicroservice.job.dto.JobDto;

import java.util.List;

public interface JobService { // created a interface to promote lose coupling
    List<Job> getAllJobs(String cmpid);
    Job createJob(JobDto j,String cmpid);
    List<Job> getBySearch(String term);
    Job updateJob(String id,JobDto j,String cmpid);
//    Job updateJobDetails(String id);
    Job deleteJob(String id);
    Job getJobById(String id);
    Integer getAllJobsByCompanyId(String cmpid);
}
