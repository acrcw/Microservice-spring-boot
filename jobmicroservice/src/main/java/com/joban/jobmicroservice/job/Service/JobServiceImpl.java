package com.joban.jobmicroservice.job.Service;


import com.joban.jobmicroservice.clients.CompanyClient;
import com.joban.jobmicroservice.external.Company;
import com.joban.jobmicroservice.job.model.Job;
import com.joban.jobmicroservice.job.dto.JobDto;
import com.joban.jobmicroservice.job.repository.JobRepository;
import com.joban.jobmicroservice.job.repository.JobSearchImpl;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service// (lifecycle managed by spring) AutoWired will only work if This class is declared as a component
public class JobServiceImpl implements JobService {

    @Autowired // spring framework will create a class that implements JobRepository interface and also creates a object of that class
    JobRepository repo;

    @Autowired
    JobSearchImpl sRepo;

//    @Autowired
//    RestTemplate rst;
    int nor;

    @Autowired
    CompanyClient companyClient;


    @Override
    public List<Job> getAllJobs(String cmpid) {
        return repo.getAllJobsByCompany(cmpid);
    }

    @Override
//    @CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
//    @Retry(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name="companyBreaker",fallbackMethod = "companyBreakerFallback") // after the limit is reached the fallback method is executed
    public Job createJob(JobDto j,String cmpid) {
        System.out.println("Retrying >>>" + ++nor);
            Company cp = companyClient.getCompany(cmpid);
//        RestTemplate rst =  new RestTemplate();
//        Company cp = rst.getForObject("http://COMPANYMICROSERVICE:8081/api/v1/company/"+cmpid,Company.class);
        if(cp!=null)
        {
            return repo.save(new Job(j.getTitle(),j.getDescription(),j.getMinSalary(),j.getMaxSalary(),j.getLocation(),j.getJobType(),j.getSkills(),j.getExp(),cp.getId()));
        }
        return null;
    }

    public Job companyBreakerFallback(JobDto j,String cmpid,Throwable ex){
        return null;
    }

    @Override
    public List<Job> getBySearch(String term) {
        return sRepo.searchByTerm(term);
    }

    @Override
    @RateLimiter(name="companyBreaker",fallbackMethod = "companyBreakerFallback") // after the limit is reached the fallback method is executed
    public Job updateJob(String id,JobDto j,String cmpid) {
//        RestTemplate rst =  new RestTemplate();
//        Company cp = rst.getForObject("http://COMPANYMICROSERVICE:8081/api/v1/company/"+cmpid,Company.class);
        Company cp = companyClient.getCompany(cmpid);
        Job ob1 = repo.findById(id).orElse(null);
        if(j!=null && ob1!=null && cp!=null)
        {
            ob1.setDescription(j.getDescription());
            ob1.setJobType(j.getJobType());
            ob1.setLocation(j.getLocation());
            ob1.setTitle(j.getTitle());
            ob1.setMaxSalary(j.getMaxSalary());
            ob1.setMinSalary(j.getMaxSalary());
            ob1.setSkills(j.getSkills());
            ob1.setExp(j.getExp());
            ob1.setCompany(cp.getId());

            return repo.save(ob1);
        }
        return null;
    }

//    @Override
//    public Job updateJobDetails(String id) {
//        return null;
//    }

    @Override
    public Job deleteJob(String id) {
        Job j = repo.findById(id).orElse(null);
        if(j!=null)
        {
            repo.deleteById(id);
            return j;
        }
        return null;

    }

    @Override
    public Job getJobById(String id) {
        return repo.findById(id).orElse(null);

    }

    @Override
    public Integer getAllJobsByCompanyId(String cmpid) {
        List<Job> ls=repo.getAllJobsByCompany(cmpid);
        return ls.size();
    }
}
