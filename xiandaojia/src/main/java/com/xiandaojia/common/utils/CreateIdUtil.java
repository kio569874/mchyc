package com.xiandaojia.common.utils;

import java.util.UUID;

/**
 * 
 * @author yc
 *
 */
public class CreateIdUtil {

	/**
	 * 
	 * 
	 * @param prex
	 * @return
	 */
	public static String createId() {
		return createId(null);
	}

	/**
	 * 
	 * 
	 * @param prex
	 * @return
	 */
	public static String createId(String prex) {
		// TODO 后续不能使用uuid
		return UUID.randomUUID().toString();
	}

}
