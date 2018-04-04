package com.xiandaojia.common.enums;

public enum NoticeTypeEnum {

	/**
	 * url 点击公告跳转到一个url
	 */
	JUMP_URL("01"),

	/**
	 * 弹窗,点击弹窗
	 */
	POP_WINDOWS("02"),
	/**
	 * 不跳转
	 */
	NO_JUMP("03");

	String type = "";

	private NoticeTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {

		return this.type;
	}
}
