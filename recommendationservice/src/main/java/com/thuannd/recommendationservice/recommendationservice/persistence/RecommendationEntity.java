package com.thuannd.recommendationservice.recommendationservice.persistence;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RecommendationEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Version
    private Long version;

    private int productId;
    private int recommendationId;
    private String author;
    private int rate;
    private String content;

    public RecommendationEntity() {
    }

    public RecommendationEntity(String id, Long version, int productId, int recommendationId, String author, int rate, String content) {
        this.id = id;
        this.version = version;
        this.productId = productId;
        this.recommendationId = recommendationId;
        this.author = author;
        this.rate = rate;
        this.content = content;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}