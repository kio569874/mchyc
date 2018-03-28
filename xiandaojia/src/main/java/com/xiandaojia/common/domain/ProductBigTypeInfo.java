package com.xiandaojia.common.domain;

import java.util.Date;

public class ProductBigTypeInfo {
    private Long bigtypeId;

    private String bigtypeName;

    private Short bigtypeSeqno;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String operator;

    public Long getBigtypeId() {
        return bigtypeId;
    }

    public void setBigtypeId(Long bigtypeId) {
        this.bigtypeId = bigtypeId;
    }

    public String getBigtypeName() {
        return bigtypeName;
    }

    public void setBigtypeName(String bigtypeName) {
        this.bigtypeName = bigtypeName == null ? null : bigtypeName.trim();
    }

    public Short getBigtypeSeqno() {
        return bigtypeSeqno;
    }

    public void setBigtypeSeqno(Short bigtypeSeqno) {
        this.bigtypeSeqno = bigtypeSeqno;
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
}