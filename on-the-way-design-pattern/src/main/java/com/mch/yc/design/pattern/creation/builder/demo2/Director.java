package com.mch.yc.design.pattern.creation.builder.demo2;

/**
 * 构造过程-导演者 担任这个角色的类调用具体建造者角色以创建产品对象
 * 
 * @author yc
 *
 */
public class Director {
	/**
	 * 持有当前需要使用的建造器对象
	 */
	private Builder builder;

	/**
	 * 构造方法，传入建造器对象
	 * 
	 * @param builder
	 *            建造器对象
	 */
	public Director(Builder builder) {
		this.builder = builder;
	}

	/**
	 * 产品构造方法，负责调用各个零件建造方法
	 */
	public void construct() {
		builder.buildPart1();
		builder.buildPart2();
	}
}
