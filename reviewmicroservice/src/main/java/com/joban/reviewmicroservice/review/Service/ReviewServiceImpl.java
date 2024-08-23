package com.joban.reviewmicroservice.review.Service;


import com.joban.reviewmicroservice.clients.CompanyClient;
import com.joban.reviewmicroservice.external.Company;
import com.joban.reviewmicroservice.review.model.Review;
import com.joban.reviewmicroservice.review.dto.ReviewDto;
import com.joban.reviewmicroservice.review.repository.ReviewRepository;
import com.joban.reviewmicroservice.review.repository.ReviewSearchImpl;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service// (lifecycle managed by spring) AutoWired will only work if This class is declared as a component
public class ReviewServiceImpl implements ReviewService {

    @Autowired // spring framework will create a class that implements JobRepository interface and also creates a object of that class
    ReviewRepository repo;

    @Autowired
    ReviewSearchImpl sRepo;

//    @Autowired
//    RestTemplate rst;

    @Autowired
    CompanyClient companyClient;

    int cnt;


    @Override
    public List<Review> getAllReviewsByCompanyId(String cid) {
        return repo.getAllReviewsByCompany(cid);
    }

    @Override
    @RateLimiter(name="companyBreaker",fallbackMethod = "companyBreakerFallback") // after the limit is reached the fallback method is executed
    public Review createReview(ReviewDto r,String cmpid) {
//        String title, String description, String minSalary, String maxSalary, String location, String jobType, String[] skills

//        RestTemplate rst =  new RestTemplate();
//        Company cp = rst.getForObject("http://COMPANYMICROSERVICE:8081/api/v1/company/"+cmpid, Company.class);
//        System.out.println("Count >>>" +cnt);
        Company cp = companyClient.getCompany(cmpid);
        if(cp!=null)
            return repo.save(new Review(r.getTitle(),r.getDescription(),r.getRating(),cp.getId()));
        return null;
    }
    public Review companyBreakerFallback(ReviewDto r,String cmpid,Throwable ex){
        return null;
    }

    @Override
    public List<Review> getReviewBySearch(String term) {
        return sRepo.searchByTerm(term);
    }

    @Override
    @RateLimiter(name="companyBreaker",fallbackMethod = "companyBreakerFallback") // after the limit is reached the fallback method is executed
    public Review updateReview(String id, ReviewDto j,String cmpid) {
//        RestTemplate rst =  new RestTemplate();
//        Company cp = rst.getForObject("http://COMPANYMICROSERVICE:8081/api/v1/company/"+cmpid, Company.class);
        Company cp = companyClient.getCompany(cmpid);

        Review ob1 = repo.findById(id).orElse(null);
        if(j!=null && ob1!=null && cp!=null)
        {
            ob1.setDescription(j.getDescription());
            ob1.setRating(j.getRating());
            ob1.setTitle(j.getTitle());
            ob1.setCompany(cp.getId());
            return repo.save(ob1);
        }
        return null;
    }

//    @Override
//    public Review updateReviewDetails(String id) {
//        return null;
//    }

    @Override
    public Review deleteReview(String id) {
        Review j = repo.findById(id).orElse(null);
        if(j!=null)
        {
            repo.deleteById(id);
            return j;
        }
        return null;

    }

    @Override
    public Review getReviewById(String id) {

        return repo.findById(id).orElse(null);


    }

    @Override
    public Double getAvgRatingByCompanyId(String cid) {
        List<Review> reviews= repo.getAllReviewsByCompany(cid);
//        double sum=0;
//        for(Review r: reviews)
//        {
//            sum+=r.getRating();
//        }
//        return sum/reviews.size();
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);

    }
}
