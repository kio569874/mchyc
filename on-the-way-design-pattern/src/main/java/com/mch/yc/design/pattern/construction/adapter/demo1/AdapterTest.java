package com.mch.yc.design.pattern.construction.adapter.demo1;

/**
 * 适配器测试
 * 
 * @author yc
 *
 */
public class AdapterTest {
	public static void main(String[] args) {
		ITargetable target = new Adapter();
		target.method1();
		target.method2();
	}
}
