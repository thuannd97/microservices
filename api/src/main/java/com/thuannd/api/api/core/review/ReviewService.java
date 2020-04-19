package com.thuannd.api.api.core.review;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewService {

    @GetMapping(value = "/review", produces = "application/json")
    List<Review> getReviews(@RequestParam(value = "productId", required = true) int productId);

    @PostMapping("/review")
    Review createReview(@RequestBody Review review);

    @DeleteMapping("/review/{id}")
    void delereReview(@PathVariable("id") Integer reviewId);

}