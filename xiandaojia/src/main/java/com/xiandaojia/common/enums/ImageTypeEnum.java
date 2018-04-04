package com.xiandaojia.common.enums;

public enum ImageTypeEnum {

	/**
	 * 轮播图
	 */
	IAMGE_01("01");


	String type = "";

	private ImageTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {

		return this.type;
	}
}
