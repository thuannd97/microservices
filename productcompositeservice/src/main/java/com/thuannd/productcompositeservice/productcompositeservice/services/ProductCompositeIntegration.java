package com.thuannd.productcompositeservice.productcompositeservice.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuannd.api.api.core.product.Product;
import com.thuannd.api.api.core.product.ProductService;
import com.thuannd.api.api.core.recommendation.Recommendation;
import com.thuannd.api.api.core.recommendation.RecommendationService;
import com.thuannd.api.api.core.review.Review;
import com.thuannd.api.api.core.review.ReviewService;
import com.thuannd.util.util.exceptions.InvalidInputException;
import com.thuannd.util.util.exceptions.NotFoundException;
import com.thuannd.util.util.http.HttpErrorInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductCompositeIntegration implements ProductService, RecommendationService, ReviewService {

    public static final Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    
    private String productServiceUrl;
    private String recommendationServiceUrl;
    private String reviewServiceUrl;


    public ProductCompositeIntegration(
        @Value("${app.product-service.host}")String productServiceHost,
        @Value("${app.product-service.port}")int productServicePort,
        @Value("${app.recommendation-service.host}")String recommendationServiceHost,
        @Value("${app.recommendation-service.port}")int recommendationServicePort,
        @Value("${app.review-service.host}")String reviewServiceHost,
        @Value("${app.review-service.port}")int reviewServicePort
    ){
        this.productServiceUrl = "http://" + productServiceHost + ":" + productServicePort + "/product/";
        this.recommendationServiceUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort + "/recommendation/";
        this.reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort + "/review/";
    }

    private String getErrorMessage(HttpClientErrorException ex){
        try {
            return mapper.readValue(
                ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException e) {
            return ex.getMessage();
        }
    }

    @Override
    public List<Review> getReviews(int productId) {
        try {
            String url = reviewServiceUrl + productId;

            LOG.debug("Will call getReviews API on URL: {}", url);
            List<Review> reviews = restTemplate.exchange(url, HttpMethod.GET, null, 
            new ParameterizedTypeReference<List<Review>>() {}).getBody();
            LOG.debug("Found {} reviews for a product with id: {}", reviews.size(), productId);
            return reviews;
        } catch (Exception e) {
            LOG.warn("Got an exception while requesting reviews, return zero reviews: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        try {
            String url = recommendationServiceUrl + productId;
            LOG.debug("Will call getRecommendations API on URL: {}", url);
            List<Recommendation> recommendations = restTemplate.exchange(url, HttpMethod.GET, null, 
                                                                        new ParameterizedTypeReference<List<Recommendation>>() {}).getBody();
            LOG.debug("Found {} recommendations for a product with id: {}", recommendations.size(), productId);
            return recommendations;                                                            
        } catch (Exception e) {
            LOG.warn("Got an exception while requesting recommendations, return zero commendations: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Product getProduct(int productId) {
        try {
            String url = productServiceUrl + productId;
            LOG.debug("Will call getProduct API on URL: {}", url);

            Product product = restTemplate.getForObject(url, Product.class);
            LOG.debug("Found a product with id: {}", product.getProductId());

            return product;
        } catch (HttpClientErrorException ex) {
            switch (ex.getStatusCode()) {
                case NOT_FOUND:
                    throw new NotFoundException(getErrorMessage(ex));
                case UNPROCESSABLE_ENTITY:
                    throw new InvalidInputException(getErrorMessage(ex));
                default:
                    LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                    LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                }
        }
        return null;
    }

}