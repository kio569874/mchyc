package com.xiandaojia.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.Contants;
import com.xiandaojia.common.domain.PaginationDto;

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
	 * 成功结果
	 * @return
	 */
	protected <T> String getSuccessPageResultMsg(PaginationDto<T> pageDto) {
		JSONObject resultJson = new JSONObject();
		resultJson.put(Contants.RET_CODE, Contants.RET_CODE_SUCCESS);
		resultJson.put(Contants.RET_MSG, Contants.RET_MSG_SUCCESS);
		resultJson.put("totalCount", pageDto.getTotalCount());
		resultJson.put("pageSize", pageDto.getPageSize());
		resultJson.put("currentPage", pageDto.getCurrentPage());
		resultJson.put("totalPages", pageDto.getTotalPages());
		if (pageDto.getData() != null) {
			resultJson.put(Contants.DATA, pageDto.getData());
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
	
	/**
	 * 获取请求ip地址
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected String getIpAddr(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
}
