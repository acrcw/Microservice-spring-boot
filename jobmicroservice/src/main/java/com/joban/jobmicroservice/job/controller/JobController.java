package com.joban.jobmicroservice.job.controller;

import com.joban.jobmicroservice.job.Service.JobService;
import com.joban.jobmicroservice.job.messaging.JobMessageProducer;
import com.joban.jobmicroservice.job.model.Job;
import com.joban.jobmicroservice.job.dto.JobDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@RequestMapping("/company/{cmpid}")
@RestController
public class JobController {


    JobService svc;
    private JobMessageProducer jobMessageProducer;


    public JobController(JobService svc, JobMessageProducer jobMessageProducer) {
        // since i have marked the service class with @Service spring boot will auto create the instance during creation and inject it
        // i just need to create a constructor in controller
        this.svc = svc;
        this.jobMessageProducer = jobMessageProducer;
    }

    // to get all the jobs by company id✅✅
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs(@RequestParam String company){
        List<Job> ls = svc.getAllJobs(company);
        if(ls!=null)
        {
            return new ResponseEntity<List<Job>>(ls,HttpStatus.OK);
        }
        return new ResponseEntity<List<Job>>(HttpStatus.SERVICE_UNAVAILABLE);

    }

    // to create a job ✅✅
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@Valid @RequestBody JobDto j,@RequestParam String company){
        Job rs = svc.createJob(j,company);
        if(rs!=null)
        {
            jobMessageProducer.sendMessage(rs);
            return new ResponseEntity<Job>(rs,HttpStatus.CREATED);
        }
        return new ResponseEntity<Job>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    // to search for a job ✅
    @GetMapping("/jobs/search/{term}")
    public ResponseEntity<List<Job>> getBySearch(@PathVariable String term,@RequestParam String company)
    {
//        System.out.println("serached term >> " + term);
        List<Job> jList =svc.getBySearch(term);
        if(jList!=null)
        {
            return new ResponseEntity<List<Job>>(jList,HttpStatus.OK);
        }
        return new ResponseEntity<List<Job>>(HttpStatus.SERVICE_UNAVAILABLE);
    }
    // to update the created job post fully ✅✅
    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable String id,@RequestBody JobDto j,@RequestParam String company)
    {
        Job rv=svc.updateJob(id,j,company);
        if(rv!=null){
            jobMessageProducer.sendMessage(rv);
            return new ResponseEntity<>(rv,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }


//    // to partially update the job post
//    @PatchMapping("/jobs/{id}")
//    public ResponseEntity<Job> updateJobDetails(@PathVariable String id)
//    {
////       return svc.updateJobDetails(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    // to delete a job ✅✅
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Job> deleteJobById(@PathVariable String id)
    {
        Job j =svc.deleteJob(id);
        if(j!=null)
        {
            jobMessageProducer.sendMessage(j);
            return new ResponseEntity<>(j,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // to get a job by id ✅✅
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id)
    {
        Job j =svc.getJobById(id);
        if(j!=null)
        {
            return new ResponseEntity<>(j,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // to get all the jobs by company id✅✅
    @GetMapping("/jobs/count")
    public ResponseEntity<Integer> getAllJobsByCompanyId(@RequestParam String company){
        Integer cnt = svc.getAllJobsByCompanyId(company);
        if(cnt!=null)
        {
            return new ResponseEntity<Integer>(cnt,HttpStatus.OK);
        }
        return new ResponseEntity<Integer>(HttpStatus.SERVICE_UNAVAILABLE);

    }


}
