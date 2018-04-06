package com.xiandaojia.common.domain;

public class ProductInformationRelation {
    private Long id;

    private Long productId;

    private Long informationId;

    private Short informationSeqno;

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

    public Long getInformationId() {
        return informationId;
    }

    public void setInformationId(Long informationId) {
        this.informationId = informationId;
    }

    public Short getInformationSeqno() {
        return informationSeqno;
    }

    public void setInformationSeqno(Short informationSeqno) {
        this.informationSeqno = informationSeqno;
    }
}