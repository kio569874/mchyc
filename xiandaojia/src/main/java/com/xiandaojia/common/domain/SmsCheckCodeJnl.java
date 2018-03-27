package com.xiandaojia.common.domain;

import java.io.Serializable;
import java.util.Date;

public class SmsCheckCodeJnl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String phoneNo;

	private String checkCode;

	private String smsToken;

	private String checkcodeType;

	private String appKey;

	private Integer validDuration;

	private Date createTime;

	private String checkResult;

	private Integer checkErrorNum;

	private Date checkPassTime;

	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo == null ? null : phoneNo.trim();
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode == null ? null : checkCode.trim();
	}

	public String getSmsToken() {
		return smsToken;
	}

	public void setSmsToken(String smsToken) {
		this.smsToken = smsToken == null ? null : smsToken.trim();
	}

	public String getCheckcodeType() {
		return checkcodeType;
	}

	public void setCheckcodeType(String checkcodeType) {
		this.checkcodeType = checkcodeType == null ? null : checkcodeType.trim();
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey == null ? null : appKey.trim();
	}

	public Integer getValidDuration() {
		return validDuration;
	}

	public void setValidDuration(Integer validDuration) {
		this.validDuration = validDuration;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult == null ? null : checkResult.trim();
	}

	public Integer getCheckErrorNum() {
		return checkErrorNum;
	}

	public void setCheckErrorNum(Integer checkErrorNum) {
		this.checkErrorNum = checkErrorNum;
	}

	public Date getCheckPassTime() {
		return checkPassTime;
	}

	public void setCheckPassTime(Date checkPassTime) {
		this.checkPassTime = checkPassTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}