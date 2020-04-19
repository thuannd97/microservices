package com.thuannd.recommendationservice.recommendationservice.services;

import com.thuannd.api.api.core.recommendation.Recommendation;
import com.thuannd.recommendationservice.recommendationservice.persistence.RecommendationEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {

    RecommendationMapper INSTANCE = Mappers.getMapper(RecommendationMapper.class);

    @Mapping(target = "serviceAddress", ignore = true)
    Recommendation entityToApi(RecommendationEntity recommendationEntity);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    RecommendationEntity apiToEntity(Recommendation recommendation);

}