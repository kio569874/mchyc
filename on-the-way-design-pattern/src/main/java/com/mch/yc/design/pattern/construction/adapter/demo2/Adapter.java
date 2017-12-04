package com.mch.yc.design.pattern.construction.adapter.demo2;

/**
 * 适配器类，继承源类，实现目标接口
 * 
 * @author yc
 *
 */
public class Adapter implements ITargetable {

	private Source source;

	public Adapter(Source source) {
		this.source = source;
	}

	@Override
	public void method2() {
		System.out.println("this is the targetable method");
	}

	@Override
	public void method1() {
		source.method1();

	}

}
