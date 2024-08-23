package com.joban.companymicroservice.clients;

import com.joban.companymicroservice.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="REVIEWMICROSERVICE")
public interface ReviewClient {
//     rst.exchange("http://REVIEWMICROSERVICE:8083/api/v1/company/" + c.getId() + "/reviews",
//    HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
//    });
    @GetMapping("/api/v1/reviews")
    List<Review> getReviews(@RequestParam("company") String cid);
    @GetMapping("/api/v1//reviews/avgRating")
    Double getAvgRatingByCompanyId(@RequestParam("company") String cid);

    // client side load load balancing is done automatically bu open fiegn
}
