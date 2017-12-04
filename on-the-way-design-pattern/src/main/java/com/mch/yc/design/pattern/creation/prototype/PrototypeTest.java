package com.mch.yc.design.pattern.creation.prototype;

import java.io.Serializable;

/**
 * 也可以接口继承Cloneable，那么接口的实现类都可以进行super.clone()，克隆对象
 * 
 * @author yc
 *
 */
public class PrototypeTest implements Cloneable, Serializable {

	public PrototypeTest clone() throws CloneNotSupportedException {
		PrototypeTest prototypeTest = (PrototypeTest) super.clone();

		return prototypeTest;

	}

}
