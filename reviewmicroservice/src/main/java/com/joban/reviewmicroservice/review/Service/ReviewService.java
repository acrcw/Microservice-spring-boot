package com.joban.reviewmicroservice.review.Service;


import com.joban.reviewmicroservice.review.model.Review;
import com.joban.reviewmicroservice.review.dto.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReviewService { // created a interface to promote lose coupling
    List<Review> getAllReviewsByCompanyId(String cid);
    Review createReview(ReviewDto r,String cmpid);
    List<Review> getReviewBySearch(String term);
    Review updateReview(String id, ReviewDto r,String cmpid);
//    Review updateReviewDetails(String id);
    Review deleteReview(String id);
    Review getReviewById(String id);
    Double getAvgRatingByCompanyId(String cid);
}
