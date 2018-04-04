package com.xiandaojia.common.enums;

public enum StatusEnum {
	/**
	 * 启动
	 */
	START("0"),

	/**
	 * 停用
	 */
	STOP("1");

	String status = "";

	private StatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {

		return this.status;
	}

}
