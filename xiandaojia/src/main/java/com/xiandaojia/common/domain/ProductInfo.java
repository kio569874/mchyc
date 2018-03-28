package com.xiandaojia.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductInfo implements Serializable{
    private Long productId;

    private String productName;

    private Long smalltypeId;

    private BigDecimal productPrice;

    private String productUrl;

    private Integer productSeqno;

    private String priceUnit;

    private Integer productNum;

    private String isDiscount;

    private BigDecimal productDiscount;

    private String productContent;

    private String nutrientContent;

    private String peopleSuitble;

    private String productInformation;

    private String status;

    private Long version;

    private String productDesc;

    private Date createTime;

    private Date updateTime;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Long getSmalltypeId() {
        return smalltypeId;
    }

    public void setSmalltypeId(Long smalltypeId) {
        this.smalltypeId = smalltypeId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl == null ? null : productUrl.trim();
    }

    public Integer getProductSeqno() {
        return productSeqno;
    }

    public void setProductSeqno(Integer productSeqno) {
        this.productSeqno = productSeqno;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit == null ? null : priceUnit.trim();
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(String isDiscount) {
        this.isDiscount = isDiscount == null ? null : isDiscount.trim();
    }

    public BigDecimal getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(BigDecimal productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent == null ? null : productContent.trim();
    }

    public String getNutrientContent() {
        return nutrientContent;
    }

    public void setNutrientContent(String nutrientContent) {
        this.nutrientContent = nutrientContent == null ? null : nutrientContent.trim();
    }

    public String getPeopleSuitble() {
        return peopleSuitble;
    }

    public void setPeopleSuitble(String peopleSuitble) {
        this.peopleSuitble = peopleSuitble == null ? null : peopleSuitble.trim();
    }

    public String getProductInformation() {
        return productInformation;
    }

    public void setProductInformation(String productInformation) {
        this.productInformation = productInformation == null ? null : productInformation.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}