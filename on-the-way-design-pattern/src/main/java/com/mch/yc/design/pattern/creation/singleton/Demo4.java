package com.mch.yc.design.pattern.creation.singleton;

/**
 * 4、饿汉式单例
 * 
 * @author yc
 *
 */
public class Demo4 {

	//对于一个final变量。  
    // 如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；  
    // 如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。  
	private final static Demo4 instance = new Demo4();

	private Demo4() {

	}

	public static Demo4 getInstance() {
		return instance;
	}

}
