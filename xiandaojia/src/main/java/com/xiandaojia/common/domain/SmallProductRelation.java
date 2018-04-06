package com.xiandaojia.common.domain;

public class SmallProductRelation {
    private Long id;

    private Long productId;

    private Long smalltypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSmalltypeId() {
        return smalltypeId;
    }

    public void setSmalltypeId(Long smalltypeId) {
        this.smalltypeId = smalltypeId;
    }
}