package com.xiandaojia.common.utils;

import java.util.UUID;

public class SmsCheckCodeUtil {

	/**
	 * 生成验证码
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static String productCheckCode(int min, int max) {
		int randNum = min + (int) (Math.random() * ((max - min) + 1));
		String checkCode = randNum + "";
		while (checkCode.length() < 6) {
			checkCode = checkCode + "0";
		}
		return checkCode;
	}

	public static String productToken() {
		return UUID.randomUUID().toString();
	}
}
