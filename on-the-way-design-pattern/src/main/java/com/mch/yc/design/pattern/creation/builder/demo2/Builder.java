package com.mch.yc.design.pattern.creation.builder.demo2;

/**
 * 构造者接口
 * @author yc
 *
 */
public interface Builder {
	
	public void buildPart1();

	public void buildPart2();

	public Product retrieveResult();
}
