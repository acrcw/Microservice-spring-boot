package com.joban.reviewmicroservice.review.repository;


import com.joban.reviewmicroservice.review.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends MongoRepository<Review,String> {
    List<Review> getAllReviewsByCompany(String cid);
}
