package com.thuannd.recommendationservice.recommendationservice.services;

import java.util.ArrayList;
import java.util.List;

import com.thuannd.api.api.core.recommendation.Recommendation;
import com.thuannd.api.api.core.recommendation.RecommendationService;
import com.thuannd.recommendationservice.recommendationservice.persistence.RecommendationEntity;
import com.thuannd.recommendationservice.recommendationservice.persistence.RecommendationRepository;
import com.thuannd.util.util.exceptions.InvalidInputException;
import com.thuannd.util.util.http.ServiceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommandationServiceImpl implements RecommendationService {

    private static final Logger LOG = LoggerFactory.getLogger(RecommandationServiceImpl.class);

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        if(productId < 1) throw new InvalidInputException("Invalid productId: " + productId);
        if(productId == 113){
            LOG.debug("No recommendations found for productId: {}", productId);
            return new ArrayList<>();
        }

        List<Recommendation> list = new ArrayList<>();
        list.add(new Recommendation(productId, 1, "Author 1", 1, "Content 1", serviceUtil.getServiceAddress()));
        list.add(new Recommendation(productId, 2, "Author 2", 2, "Content 2", serviceUtil.getServiceAddress()));
        list.add(new Recommendation(productId, 3, "Author 3", 3, "Content 3", serviceUtil.getServiceAddress()));

        LOG.debug("/recommendation response size: {}", list.size());

        return list;
    }

    @Override
    public Recommendation createRecommendation(Recommendation recommendation) {
        RecommendationEntity recommendationEntity = RecommendationMapper.INSTANCE.apiToEntity(recommendation);
        RecommendationEntity newEntity = recommendationRepository.save(recommendationEntity);

        LOG.info("create recommendation with recommendationId {}", newEntity.getRecommendationId());

        return RecommendationMapper.INSTANCE.entityToApi(newEntity);
    }

    @Override
    public void deleteRecommendation(Integer productId) {
        recommendationRepository.deleteAll(recommendationRepository.findByProductId(productId));
    }

}