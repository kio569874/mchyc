package com.xiandaojia.common.exception;

/**
 * 支付服务异常类
 * 
 * @author mchyc
 *
 */
public class PayException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PayException() {
		super();
	}

	public PayException(String message) {
		super(message);
	}
}
