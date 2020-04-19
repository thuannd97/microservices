package com.thuannd.productservice.productservice.services;

import com.thuannd.api.api.core.product.Product;
import com.thuannd.api.api.core.product.ProductService;
import com.thuannd.productservice.productservice.persistence.ProductEntity;
import com.thuannd.productservice.productservice.persistence.ProductRepository;
import com.thuannd.util.util.exceptions.InvalidInputException;
import com.thuannd.util.util.exceptions.NotFoundException;
import com.thuannd.util.util.http.ServiceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceImpl implements ProductService {

    public static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProduct(int productId) {
        LOG.debug("/product return the found product for productId={}", productId);
        if(productId < 1) throw new InvalidInputException("Invalid productId: " + productId);
        if(productId ==  13) throw new NotFoundException("No product found for productId: " + productId);
        return new Product(productId, "name-" + productId, 123, serviceUtil.getServiceAddress());
    }

    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = ProductMapper.INSTANCE.apiToEntity(product);
        ProductEntity newEntity = productRepository.save(productEntity);

        LOG.debug("createProduct: entity created for productId: {}", product.getProductId());

        return ProductMapper.INSTANCE.entityToApi(newEntity);
    }

    @Override
    public void deleteProduct(Integer id) {
        LOG.debug("delete product with productId: {}", id);
        productRepository.findByProductId(id).ifPresent(product -> productRepository.delete(product));
    }

}   