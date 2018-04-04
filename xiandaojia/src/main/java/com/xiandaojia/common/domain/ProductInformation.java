package com.xiandaojia.common.domain;

import java.io.Serializable;
import java.util.Date;

public class ProductInformation implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long informationId;

    private String informationName;

    private Long productId;

    private String informationContent;

    private Short informationSeqno;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent == null ? null : informationContent.trim();
    }

    public Short getInformationSeqno() {
        return informationSeqno;
    }

    public void setInformationSeqno(Short informationSeqno) {
        this.informationSeqno = informationSeqno;
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