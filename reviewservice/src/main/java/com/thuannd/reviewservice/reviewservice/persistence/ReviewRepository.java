package com.thuannd.reviewservice.reviewservice.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Integer>{

    List<ReviewEntity> findByProductId(Integer productId);

}