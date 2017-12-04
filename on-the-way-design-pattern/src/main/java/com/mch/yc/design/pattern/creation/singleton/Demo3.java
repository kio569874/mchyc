package com.mch.yc.design.pattern.creation.singleton;

/**
 * 3、线程安全的懒汉式单例，双重锁形式
 * 
 * @author yc
 *
 */
public class Demo3 {

	volatile private static Demo3 instance;

	private Demo3() {

	}

	public static Demo3 getInstance() {
		if (null == instance) {
			synchronized (Demo3.class) {
				if (instance == null) {
					instance = new Demo3();
				}
			}
		}
		return instance;
	}

	public Object readResolve() {
		return instance;
	}

}
