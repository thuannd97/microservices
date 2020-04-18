package com.thuannd.productservice.productservice.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long>{

}