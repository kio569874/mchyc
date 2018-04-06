package com.xiandaojia.common.domain;

import java.util.Date;

public class ProductInformation {
    private Long informationId;

    private String informationName;

    private String informationContent;

    private String informationDesc;

    private Date createTime;

    private Date updateTime;

    public Long getInformationId() {
        return informationId;
    }

    public void setInformationId(Long informationId) {
        this.informationId = informationId;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName == null ? null : informationName.trim();
    }

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent == null ? null : informationContent.trim();
    }

    public String getInformationDesc() {
        return informationDesc;
    }

    public void setInformationDesc(String informationDesc) {
        this.informationDesc = informationDesc == null ? null : informationDesc.trim();
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