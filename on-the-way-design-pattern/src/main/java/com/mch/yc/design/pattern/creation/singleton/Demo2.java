package com.mch.yc.design.pattern.creation.singleton;

/**
 * 2、 线程安全的懒汉式单例、缺点，高并发下需要排队等待，运行效率低下
 * @author yc
 *
 */
public class Demo2 {

	private static Demo2 instance;

	private Demo2() {

	}

	public synchronized static Demo2 getInstance() {
		if (null == instance) {
			instance = new Demo2();
		}
		return instance;
	}

	public Object readResolve() {
		return instance;
	}
}
