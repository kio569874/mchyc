package com.mch.yc.design.pattern.construction.adapter.demo1;

/**
 * 适配器类，继承源类，实现目标接口
 * 
 * @author yc
 *
 */
public class Adapter extends Source implements ITargetable {

	@Override
	public void method2() {
		System.out.println("this is the targetable method");
	}

}
