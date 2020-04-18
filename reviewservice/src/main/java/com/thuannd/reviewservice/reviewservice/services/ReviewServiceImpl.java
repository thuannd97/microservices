package com.thuannd.reviewservice.reviewservice.services;

import java.util.ArrayList;
import java.util.List;

import com.thuannd.api.api.core.review.Review;
import com.thuannd.api.api.core.review.ReviewService;
import com.thuannd.util.util.exceptions.InvalidInputException;
import com.thuannd.util.util.http.ServiceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public List<Review> getReviews(int productId) {
        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        if (productId == 213) {
            LOG.debug("No reviews found for productId: {}", productId);
            return  new ArrayList<>();
        }

        List<Review> list = new ArrayList<>();
        list.add(new Review(productId, 1, "Author 1", "Subject 1", "Content 1", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 2, "Author 2", "Subject 2", "Content 2", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 3, "Author 3", "Subject 3", "Content 3", serviceUtil.getServiceAddress()));

        LOG.debug("/reviews response size: {}", list.size());

        return list;
    }

}