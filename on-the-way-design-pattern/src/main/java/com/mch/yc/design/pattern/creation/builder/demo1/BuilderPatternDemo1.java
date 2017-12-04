package com.mch.yc.design.pattern.creation.builder.demo1;

import com.mch.yc.design.pattern.creation.builder.demo1.InsuranceContract;

/**
 * 常规构建者模式
 * @author yc
 *
 */
public class BuilderPatternDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 创建构建器对象
		// 设置需要的数据，然后构建保险合同对象
		InsuranceContract contract = new InsuranceContract.ConcreteBuilder(
				"9527", 123L, 456L).setPersonName("小明").setOtherData("test")
				.build();

		// 操作保险合同对象的方法
		contract.someOperation();
	}

}
