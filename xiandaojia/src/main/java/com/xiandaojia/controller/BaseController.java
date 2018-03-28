package com.xiandaojia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.Contants;

/**
 * 
 * @author mchyc
 *
 */
public class BaseController {

	/**
	 * 状态
	 */

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 成功结果
	 * 
	 * @param json
	 * @return
	 */
	protected String getSuccessResultMsg() {
		return getSuccessResultMsg(null, Contants.RET_CODE_SUCCESS, Contants.RET_MSG_SUCCESS);

	}

	/**
	 * 成功结果
	 * 
	 * @param json
	 * @return
	 */
	protected String getSuccessResultMsg(String data) {
		return getSuccessResultMsg(data, Contants.RET_CODE_SUCCESS, Contants.RET_MSG_SUCCESS);

	}

	/**
	 * 成功结果
	 * 
	 * @param json
	 * @param retCode
	 * @param retMsg
	 * @return
	 */
	protected String getSuccessResultMsg(String data, String retCode, String retMsg) {
		JSONObject resultJson = new JSONObject();
		resultJson.put(Contants.RET_CODE, retCode);
		resultJson.put(Contants.RET_MSG, retMsg);
		if (data != null) {
			JSONObject dataJson = JSONObject.parseObject(data);
			resultJson.put(Contants.DATA, dataJson);
		}
		return resultJson.toJSONString();

	}

	/**
	 * 错误结果
	 * 
	 * @param json
	 * @return
	 */
	protected String getErrorResultMsg(String retMsg) {
		return getErrorResultMsg(null, Contants.RET_CODE_FAIL, retMsg);
	}

	/**
	 * 错误结果
	 * 
	 * @param json
	 * @param retCode
	 * @param retMsg
	 * @return
	 */
	protected String getErrorResultMsg(String data, String retCode, String retMsg) {
		JSONObject dataJson = JSONObject.parseObject(data);
		JSONObject resultJson = new JSONObject();
		resultJson.put(Contants.RET_CODE, retCode);
		resultJson.put(Contants.RET_MSG, retMsg);
		resultJson.put(Contants.DATA, dataJson);
		return resultJson.toJSONString();

	}
}
