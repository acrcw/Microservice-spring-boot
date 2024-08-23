package com.joban.companymicroservice.company.Service;


import com.joban.companymicroservice.clients.JobClient;
import com.joban.companymicroservice.clients.ReviewClient;
import com.joban.companymicroservice.company.dto.JobMessageDto;
import com.joban.companymicroservice.company.dto.ReviewMessageDto;
import com.joban.companymicroservice.company.model.Company;
import com.joban.companymicroservice.company.dto.CompanyDto;
import com.joban.companymicroservice.company.repository.CompanyRepository;
import com.joban.companymicroservice.company.repository.CompanySearchImpl;
import com.joban.companymicroservice.external.Job;
import com.joban.companymicroservice.external.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service// (lifecycle managed by spring) AutoWired will only work if This class is declared as a component
public class CompanyServiceImpl implements CompanyService {

    @Autowired // spring framework will create a class that implements JobRepository interface and also creates a object of that class
    CompanyRepository cRepo;

//    @Autowired
//    RestTemplate rst;


    @Autowired
    CompanySearchImpl sRepo;

    @Autowired
    private ReviewClient reviewClient;

    @Autowired
    private JobClient jobClient;

    int cnt;


    @Override
    @CircuitBreaker(name="comboBreaker",fallbackMethod = "comboBreakerFallback")
    public List<Company> getAllCompanies() {
        List<Company> list =cRepo.findAll();
        System.out.println("Count is>>>>" + ++cnt);
        // Fetch jobs and reviews
        for(Company c:list)
        {
            // using openfeign instead of load balanced rest template
            List<Job> jobs = jobClient.getJobs(c.getId());
            c.setJobs(jobs);
            List<Review> reviews   = reviewClient.getReviews(c.getId());
            c.setReviews(reviews);
        }

        return list;
    }

    public List<Company> comboBreakerFallback(Throwable ex){
        return null;
    }

    @Override
    public Company createCompany(CompanyDto c) {
//        String title, String description, String minSalary, String maxSalary, String location, String jobType, String[] skills
        return cRepo.save(new Company(c.getName(),c.getDescription()));

    }

    @Override
    public List<Company> getCompanyBySearch(String term) {
        return sRepo.searchByTerm(term);
    }

    @Override
    public Company updateCompany(String id, CompanyDto j) {

        Company ob1 = cRepo.findById(id).orElse(null);
        if(j!=null && ob1!=null)
        {
            ob1.setName(j.getName());
            ob1.setDescription(j.getDescription());
            return cRepo.save(ob1);
        }
        return null;
    }

//    @Override
//    public Company updateJobDetails(String id) {
//        return null;
//    }

    @Override
    public Company deleteCompany(String id) {
        Company j = cRepo.findById(id).orElse(null);
        if(j!=null)
        {
            cRepo.deleteById(id);
            return j;

        }
        return null;

    }

    @Override
    @CircuitBreaker(name="comboBreaker",fallbackMethod = "comboBreakerFallbackWithArgs")
    public Company getCompanyById(String id) {
        Company c=cRepo.findById(id).orElse(null);
//    IMPLEMENTED WITH OPEN FIEGN CLIENT
        System.out.println("Count is>>>>" + ++cnt);
        if(c!=null)
        {
            List<Job> jobs = jobClient.getJobs(c.getId());
            c.setJobs(jobs);
            List<Review> reviews   = reviewClient.getReviews(c.getId());
            c.setReviews(reviews);
        }


        //IMPLEMENTED WITH REST TEMPLATE
//        ResponseEntity<List<Job>> rsp =
//                rst.exchange("http://JOBMICROSERVICE:8082/api/v1/company/" + c.getId() + "/jobs",
//                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Job>>() {
//                        });
//        List<Job> jobs = rsp.getBody();
//        c.setJobs(jobs);
//
//
//        ResponseEntity<List<Review>> rateResponse =   rst.exchange("http://REVIEWMICROSERVICE:8083/api/v1/company/" + c.getId() + "/reviews",
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
//                });
//        List<Review> reviews = rateResponse.getBody();
//        c.setReviews(reviews);
        return c;

    }
    public Company comboBreakerFallbackWithArgs(String id,Throwable ex){
        return null;
    }
    @Override
    @Retry(name="reviewBreaker")
    public void updateCompanyRating(ReviewMessageDto msg){
        System.out.println("msgs>>>>>"+ msg.getCompany());
        Double rating= reviewClient.getAvgRatingByCompanyId(msg.getCompany()); // get all the reviews for my company
//        System.out.println("rating>>>>>"+ rating);
        Company cmp = cRepo.findById(msg.getCompany()).orElse(null);
        if(cmp!=null)
        {
            cmp.setAvgRating(rating);
            cRepo.save(cmp);
        }


    }

    @Override
    @Retry(name="JobBreaker")
    public void updateCompanyJobCount(JobMessageDto msg) {
        System.out.println("msgs>>>>>"+ msg.getCompany());
        int count = jobClient.getJobsCountByCompanyId(msg.getCompany());
        Company cmp = cRepo.findById(msg.getCompany()).orElse(null);
        if(cmp!=null)
        {
            cmp.setJobCount(count);
            cRepo.save(cmp);
        }

    }
}
