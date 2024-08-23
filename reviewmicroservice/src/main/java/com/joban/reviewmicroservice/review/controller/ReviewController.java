package com.joban.reviewmicroservice.review.controller;


import com.joban.reviewmicroservice.review.Service.ReviewService;
import com.joban.reviewmicroservice.review.messaging.ReviewMessageProducer;
import com.joban.reviewmicroservice.review.model.Review;
import com.joban.reviewmicroservice.review.dto.ReviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
//@RequestMapping("/company/{cmpid}")
@RestController
public class ReviewController {


    ReviewService svc;
    private ReviewMessageProducer reviewMessageProducer;



    public ReviewController(ReviewService svc, ReviewMessageProducer reviewMessageProducer) {
        // since i have marked the service class with @Service spring boot will auto create the instance during creation and inject it
        // i just need to create a constructor in controller
        this.svc = svc;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    // to get all the reviews by companyid ✅✅
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviewsByCompanyId(@RequestParam String company){
        List<Review> rv = svc.getAllReviewsByCompanyId(company);
        if(rv!=null)
        {
            return new ResponseEntity<List<Review>>(rv,HttpStatus.OK);
        }
        return new ResponseEntity<List<Review>>(HttpStatus.SERVICE_UNAVAILABLE);

    }

    // to create a review by cmpid ✅✅
    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto r,@RequestParam String company){
        Review rs = svc.createReview(r,company);
        if(rs!=null)
        {
            reviewMessageProducer.sendMessage(rs);
            return new ResponseEntity<Review>(rs,HttpStatus.CREATED);
        }
        return new ResponseEntity<Review>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    // to search for a review ✅
    @GetMapping("/reviews/search/{term}")
    public ResponseEntity<List<Review>> getReviewBySearch(@PathVariable String term,@RequestParam String company)
    {
        System.out.println("serached term >> " + term);
        List<Review> jList =svc.getReviewBySearch(term);
        if(jList!=null)
        {
            return new ResponseEntity<List<Review>>(jList,HttpStatus.OK);
        }
        return new ResponseEntity<List<Review>>(HttpStatus.NOT_FOUND);
    }
    // to update the created review post fully ✅✅
    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable String id, @RequestBody ReviewDto j, @RequestParam String company)
    {
        Review rv=svc.updateReview(id,j,company);
        if(rv!=null){
            reviewMessageProducer.sendMessage(rv);
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

    // to delete a review ✅✅
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Review> deleteReviewById(@PathVariable String id)
    {
        Review rv =svc.deleteReview(id);
        if(rv!=null)
        {
            reviewMessageProducer.sendMessage(rv);
            return new ResponseEntity<>(rv,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // to get a review by id ✅✅
    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id)
    {
        Review j =svc.getReviewById(id);
        if(j!=null)
        {
            return new ResponseEntity<>(j,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // to get all the reviews by companyid ✅✅
    @GetMapping("/reviews/avgRating")
    public ResponseEntity<Double> getAvgRatingByCompanyId(@RequestParam String company){
        Double rating = svc.getAvgRatingByCompanyId(company);
        if(rating!=null)
        {
            return new ResponseEntity<Double>(rating,HttpStatus.OK);
        }
        return new ResponseEntity<Double>(HttpStatus.BAD_REQUEST);

    }


}
