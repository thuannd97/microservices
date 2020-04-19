package com.thuannd.api.api.core.recommendation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface RecommendationService {

    @GetMapping(value = "/recommendation", 
                produces = "application/json")
    List<Recommendation> getRecommendations(
        @RequestParam(value = "productId", required = true) int productId
    );

    @PostMapping("/recommendation")
    Recommendation createRecommendation(@RequestBody Recommendation recommendation);

    @DeleteMapping("/recommendation/{id}")
    void deleteRecommendation(@PathVariable("id") Integer recommendationId);

}