package com.xiandaojia.spi.sms.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiandaojia.spi.sms.ISmsHandle;

/**
 * 短信服务抽象实现
 * 
 * @author yc
 *
 */
public abstract class AbstractSmsHandler implements ISmsHandle {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 生成验证码
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public String productCheckCode(int min, int max) {
		int randNum = min + (int) (Math.random() * ((max - min) + 1));
		return randNum + "";
	}
}
