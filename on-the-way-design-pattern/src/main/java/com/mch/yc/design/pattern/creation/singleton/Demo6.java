package com.mch.yc.design.pattern.creation.singleton;

import java.util.Vector;

/**
 * 6、避免构造方法出现异常
 * @author yc
 *
 */
public class Demo6 {

	private static Demo6 instance = null;
	private Vector properties = null;

	public Vector getProperties() {
		return properties;
	}

	private Demo6() {

	}

	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new Demo6();
		}
	}

	public static Demo6 getInstance() {
		if (instance == null) {
			syncInit();
		}
		return instance;
	}

	public void updateProperties() {
		Demo6 shadow = new Demo6();
		properties = shadow.getProperties();
	}

}
