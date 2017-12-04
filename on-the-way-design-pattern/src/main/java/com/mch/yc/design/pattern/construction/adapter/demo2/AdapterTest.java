package com.mch.yc.design.pattern.construction.adapter.demo2;

/**
 * 适配器测试
 * 
 * @author yc
 *
 */
public class AdapterTest {
	public static void main(String[] args) {
		Source source = new Source();
		ITargetable target = new Adapter(source);
		target.method1();
		target.method2();
	}
}
