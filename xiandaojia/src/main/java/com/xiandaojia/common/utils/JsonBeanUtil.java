package com.xiandaojia.common.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * fastjson转换类
 * 
 * @author mchyc
 *
 */
public class JsonBeanUtil {

	@SuppressWarnings("unchecked")
	public static <T> T stringToBean(Class<T> className, String content) {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(content);
		return (T) JSONObject.toJavaObject(jsonObject, className);
	}

	public static <T> T jsonToBean(Class<T> className, JSONObject jsonObject) {
		return (T) JSONObject.toJavaObject(jsonObject, className);
	}
}
