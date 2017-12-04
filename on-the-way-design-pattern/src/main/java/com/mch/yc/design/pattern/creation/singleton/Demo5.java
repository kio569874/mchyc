package com.mch.yc.design.pattern.creation.singleton;

/**
 * 5、静态内部类实现的单例
 * 
 * @author yc
 *
 */
public class Demo5 {

	private static class Demo5Handler {
		private static Demo5 instance = new Demo5();
	}

	private Demo5() {

	}

	public static Demo5 getInstance() {
		return Demo5Handler.instance;
	}

	public Object readResolve() {
		return getInstance();
	}

}
