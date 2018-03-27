package com.xiandaojia.common.enums;

public enum CheckResultEnum {
	/**
	 * 成功
	 */
	PASS("0"),

	/**
	 * 失败
	 */
	FAIL("1");

	String status = "";

	private CheckResultEnum(String status) {
		this.status = status;
	}

	public String getStatus() {

		return this.status;
	}

}
