package com.joban.reviewmicroservice.review.repository;

import com.joban.reviewmicroservice.review.model.Review;

import java.util.List;

public interface ReviewSearch {
    List<Review> searchByTerm(String term);
}
