package com.thuannd.productcompositeservice.productcompositeservice.services;

import java.util.List;
import java.util.stream.Collectors;

import com.thuannd.api.api.composite.product.ProductAggregate;
import com.thuannd.api.api.composite.product.ProductCompositeService;
import com.thuannd.api.api.composite.product.RecommendationSummary;
import com.thuannd.api.api.composite.product.ReviewSummary;
import com.thuannd.api.api.composite.product.ServiceAddesses;
import com.thuannd.api.api.core.product.Product;
import com.thuannd.api.api.core.recommendation.Recommendation;
import com.thuannd.api.api.core.review.Review;
import com.thuannd.util.util.exceptions.NotFoundException;
import com.thuannd.util.util.http.ServiceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCompositeServiceImpl implements ProductCompositeService {

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private ProductCompositeIntegration integration;

    @Override
    public ProductAggregate getProduct(int productId) {
        Product product = integration.getProduct(productId);
        if(product == null) throw new NotFoundException("No producr found for productId: " + productId);
        List<Recommendation> recommendations = integration.getRecommendations(productId);
        List<Review> reviews = integration.getReviews(productId);

        return createProductAggregate(product, recommendations, reviews, serviceUtil.getServiceAddress());
    }

    private ProductAggregate createProductAggregate(
        Product product, List<Recommendation> recommendations, List<Review> reviews,
        String serviceAddress
    ){
        int productId = product.getProductId();
        String name = product.getName();
        int weight = product.getWeight();

        List<RecommendationSummary> recommendationSummaries = (recommendations == null) ? null :
            recommendations.stream()
                .map(r -> new RecommendationSummary(r.getRecommendationId(), r.getAuthor(), r.getRate()))
                .collect(Collectors.toList());
        
        List<ReviewSummary> reviewSummaries = (reviews == null) ? null :
            reviews.stream()
                    .map(r -> new ReviewSummary(r.getReviewId(), r.getAuthor(), r.getSubject()))
                    .collect(Collectors.toList());

        String productAddress = product.getServiceAddress();
        String reviewAddress = (reviews != null && !reviews.isEmpty()) ? reviews.get(0).getServiceAddress() : "";
        String recommendationAddress = (recommendations != null && !recommendations.isEmpty())
            ? recommendations.get(0).getServiceAddress() : "";
        ServiceAddesses serviceAddesses = new ServiceAddesses(
            serviceAddress, productAddress, reviewAddress, recommendationAddress);         

        return new ProductAggregate(productId, name, weight, recommendationSummaries, 
        reviewSummaries, serviceAddesses);
    }

}