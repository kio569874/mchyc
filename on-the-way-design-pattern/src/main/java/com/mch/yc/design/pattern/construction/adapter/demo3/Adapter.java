package com.mch.yc.design.pattern.construction.adapter.demo3;

/**
 * 适配器类，继承源类，实现目标接口
 * 
 * @author yc
 *
 */
public abstract class Adapter implements ITargetable {

	@Override
	public void method1() {
		System.out.println("abstract Adapter1");
	}

	@Override
	public void method2() {
		System.out.println("abstract Adapter2");
	}

}
