package com.thuannd.productservice.productservice.persistence;

import java.io.Serializable;

import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @org.springframework.data.annotation.Id
    private String id;
    @Version
    private Long version;
    @Indexed(unique = true)
    private Integer productId;
    private String name;
    private Integer weight;

    public ProductEntity() {
    }

    public ProductEntity(String id, Long version, Integer productId, String name, Integer weight) {
        this.id = id;
        this.version = version;
        this.productId = productId;
        this.name = name;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}