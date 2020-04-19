package com.thuannd.api.api.core.product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductService {

    @GetMapping(value = "/product/{productId}", 
                produces = "application/json")
    Product getProduct(@PathVariable int productId);

    @PostMapping("/product")
    Product createProduct(@RequestBody Product product);

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Integer id);

}