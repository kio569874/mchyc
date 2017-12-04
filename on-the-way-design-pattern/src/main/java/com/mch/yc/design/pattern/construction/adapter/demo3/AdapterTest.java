package com.mch.yc.design.pattern.construction.adapter.demo3;

/**
 * 适配器测试
 * 
 * @author yc
 *
 */
public class AdapterTest {
	public static void main(String[] args) {
		Source1 source1 = new Source1();
		Source2 source2 = new Source2();
		source1.method1();
		source1.method2();
		System.out.println("--------------------");
		source2.method1();
		source2.method2();
	}
}
