package com.xiandaojia.common.exception;

/**
 * 系统异常类-放在common中，每个微服务模块都可以继承
 * 
 * @author mchyc
 *
 */
public class SysException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SysException() {
		super();
	}

	public SysException(String message) {
		super(message);
	}
}
