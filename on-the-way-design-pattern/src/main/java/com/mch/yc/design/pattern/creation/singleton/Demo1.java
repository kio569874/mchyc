package com.mch.yc.design.pattern.creation.singleton;

/**
 * 1、普通单例模式，线程不安全
 * @author yc
 *
 */
public class Demo1 {

	private static Demo1 instance = null;

	private Demo1() {

	}

	public static Demo1 getInstance() {
		if (instance == null) {
			 //在这个地方,多线程的时候，  
            //可能A线程挂起，此属性还是null，那么B线程可能也判断条件OK也进来啦。  
            //然后A线程可以执行的时候就会new个对象，线程B也会new个对象。  
            //就不能保证内存的唯一性。也就是线程不安全  
			instance = new Demo1();
		}
		return instance;
	}

	public Object readResolve() {
		return instance;
	}
}
