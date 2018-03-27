package com.xiandaojia.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SmsSendJnl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String smsSendId;

	private String sendPhone;

	private String smsContext;

	private String sendSmsType;

	private Integer sendCount;

	private BigDecimal sendFee;

	private String feeUnit;

	private String sendReturnCode;

	private String sendReturnMessage;

	private String sendId;

	private Date sendTime;

	private Date createTime;

	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSmsSendId() {
		return smsSendId;
	}

	public void setSmsSendId(String smsSendId) {
		this.smsSendId = smsSendId == null ? null : smsSendId.trim();
	}

	public String getSendPhone() {
		return sendPhone;
	}

	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone == null ? null : sendPhone.trim();
	}

	public String getSmsContext() {
		return smsContext;
	}

	public void setSmsContext(String smsContext) {
		this.smsContext = smsContext == null ? null : smsContext.trim();
	}

	public String getSendSmsType() {
		return sendSmsType;
	}

	public void setSendSmsType(String sendSmsType) {
		this.sendSmsType = sendSmsType == null ? null : sendSmsType.trim();
	}

	public Integer getSendCount() {
		return sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public BigDecimal getSendFee() {
		return sendFee;
	}

	public void setSendFee(BigDecimal sendFee) {
		this.sendFee = sendFee;
	}

	public String getFeeUnit() {
		return feeUnit;
	}

	public void setFeeUnit(String feeUnit) {
		this.feeUnit = feeUnit == null ? null : feeUnit.trim();
	}

	public String getSendReturnCode() {
		return sendReturnCode;
	}

	public void setSendReturnCode(String sendReturnCode) {
		this.sendReturnCode = sendReturnCode == null ? null : sendReturnCode.trim();
	}

	public String getSendReturnMessage() {
		return sendReturnMessage;
	}

	public void setSendReturnMessage(String sendReturnMessage) {
		this.sendReturnMessage = sendReturnMessage == null ? null : sendReturnMessage.trim();
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId == null ? null : sendId.trim();
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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