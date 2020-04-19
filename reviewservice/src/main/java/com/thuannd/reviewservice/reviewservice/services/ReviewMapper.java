package com.thuannd.reviewservice.reviewservice.services;

import com.thuannd.api.api.core.review.Review;
import com.thuannd.reviewservice.reviewservice.persistence.ReviewEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    public ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "serviceAddress", ignore = true)
    Review entityToApi(ReviewEntity reviewEntity);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "service", ignore = true)
    })
    ReviewEntity apiToEntity(Review review);

}