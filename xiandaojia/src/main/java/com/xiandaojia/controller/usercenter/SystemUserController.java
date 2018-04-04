package com.xiandaojia.controller.usercenter;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.SystemUser;
import com.xiandaojia.common.utils.JsonBeanUtil;
import com.xiandaojia.common.utils.MD5Util;
import com.xiandaojia.controller.BaseController;
import com.xiandaojia.service.usercenter.ISystemUserService;

@RestController
@RequestMapping("systemUser")
public class SystemUserController extends BaseController {

	@Autowired
	ISystemUserService systemUserService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody String content, HttpServletRequest request) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			if (!jsonObj.containsKey("userCode") || !jsonObj.containsKey("userCode")
					|| StringUtils.isEmpty(jsonObj.get("userCode"))
					|| StringUtils.isEmpty(jsonObj.get("userPassword"))) {
				return getErrorResultMsg("用户名或密码错误");
			}
			String userCode = jsonObj.getString("userCode");
			String userPassword = jsonObj.getString("userPassword");
			userPassword = MD5Util.createMD5(userPassword);
			SystemUser systemUser = systemUserService.checkSystemUser(userCode, userPassword);
			if (systemUser != null && !StringUtils.isEmpty(systemUser.getUserCode())) {
				systemUser.setLoginTime(new Date());
				systemUser.setLoginIp(getIpAddr(request));
				systemUserService.update(systemUser);
				return getSuccessResultMsg();
			} else {
				return getErrorResultMsg("用户名或密码错误");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public String insert(@RequestBody String content) {
		try {
			SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, content);
			systemUser.setUserPassword(MD5Util.createMD5(systemUser.getUserPassword()));
			systemUserService.insert(systemUser);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody String content) {
		try {
			SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, content);
			systemUserService.delete(systemUser.getId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestBody String content) {
		try {
			SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, content);
			systemUserService.update(systemUser);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/queryListByPage", method = RequestMethod.POST)
	@ResponseBody
	public String queryListByPage(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			PaginationDto<SystemUser> paginationDto = systemUserService.querySystemUserListByPage(page, pageSize, null);
			List<SystemUser> systemUserList = paginationDto.getData();
			if (systemUserList != null && systemUserList.size() > 0) {
				JSONObject list = new JSONObject();
				JSONArray jsonArr = (JSONArray) JSONArray.toJSON(systemUserList);
				list.put("listData", jsonArr);
				return getSuccessResultMsg(list.toJSONString());
			} else {
				return getSuccessResultMsg(new JSONObject().toJSONString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}
}
