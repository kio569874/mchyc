package com.xiandaojia.common.domain;

import java.util.Date;

public class ProductSmallTypeInfo {
    private Long smalltypeId;

    private String smalltypeName;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String operator;

    private Short smallSeqno;

    public Long getSmalltypeId() {
        return smalltypeId;
    }

    public void setSmalltypeId(Long smalltypeId) {
        this.smalltypeId = smalltypeId;
    }

    public String getSmalltypeName() {
        return smalltypeName;
    }

    public void setSmalltypeName(String smalltypeName) {
        this.smalltypeName = smalltypeName == null ? null : smalltypeName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Short getSmallSeqno() {
        return smallSeqno;
    }

    public void setSmallSeqno(Short smallSeqno) {
        this.smallSeqno = smallSeqno;
    }
}