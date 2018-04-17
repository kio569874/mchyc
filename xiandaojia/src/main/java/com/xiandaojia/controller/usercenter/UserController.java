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
import com.xiandaojia.common.domain.ShoppingCart;
import com.xiandaojia.common.domain.SystemUser;
import com.xiandaojia.common.domain.User;
import com.xiandaojia.common.domain.UserAddress;
import com.xiandaojia.common.utils.JsonBeanUtil;
import com.xiandaojia.common.utils.MD5Util;
import com.xiandaojia.controller.BaseController;
import com.xiandaojia.service.usercenter.IShoppingCartService;
import com.xiandaojia.service.usercenter.ISystemUserService;
import com.xiandaojia.service.usercenter.IUserAddressService;
import com.xiandaojia.service.usercenter.IUserService;

@RestController
@RequestMapping("userCenter")
public class UserController extends BaseController {

	@Autowired
	ISystemUserService systemUserService;

	@Autowired
	IShoppingCartService shoppingCartService;

	@Autowired
	IUserService userService;

	@Autowired
	IUserAddressService userAddressService;

	@RequestMapping(value = "/systemUser/login", method = RequestMethod.POST)
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
				return getSuccessResultMsg(JSONObject.toJSONString(systemUser));
			} else {
				return getErrorResultMsg("用户名或密码错误");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/systemUser/insert", method = RequestMethod.POST)
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

	@RequestMapping(value = "/systemUser/delete", method = RequestMethod.POST)
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

	@RequestMapping(value = "/systemUser/update", method = RequestMethod.POST)
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

	@RequestMapping(value = "/systemUser/queryListByPage", method = RequestMethod.POST)
	@ResponseBody
	public String queryListByPage(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			PaginationDto<SystemUser> paginationDto = systemUserService.queryListByPage(page, pageSize, null);
			return getSuccessResultMsg(JSONObject.toJSONString(paginationDto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/shoppingCart/insert", method = RequestMethod.POST)
	@ResponseBody
	public String shoppingCartInsert(@RequestBody String content) {
		try {
			ShoppingCart shoppingCart = JsonBeanUtil.stringToBean(ShoppingCart.class, content);
			shoppingCartService.insert(shoppingCart);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/shoppingCart/delete", method = RequestMethod.POST)
	@ResponseBody
	public String shoppingCartDelete(@RequestBody String content) {
		try {
			ShoppingCart shoppingCart = JsonBeanUtil.stringToBean(ShoppingCart.class, content);
			shoppingCartService.delete(shoppingCart.getId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/shoppingCart/update", method = RequestMethod.POST)
	@ResponseBody
	public String shoppingCartUpdate(@RequestBody String content) {
		try {
			ShoppingCart t = JsonBeanUtil.stringToBean(ShoppingCart.class, content);
			shoppingCartService.update(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/shoppingCart/queryListByPage", method = RequestMethod.POST)
	@ResponseBody
	public String shoppingCartQuery(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			Long userId = jsonObj.getLong("userId");
			PaginationDto<ShoppingCart> paginationDto = shoppingCartService.queryListByPage(page, pageSize, null,
					userId);
			return getSuccessResultMsg(JSONObject.toJSONString(paginationDto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public String userLogin(@RequestBody String content, HttpServletRequest request) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			if (!jsonObj.containsKey("userAccount") || !jsonObj.containsKey("userPassword")
					|| StringUtils.isEmpty(jsonObj.get("userAccount"))
					|| StringUtils.isEmpty(jsonObj.get("userPassword"))) {
				return getErrorResultMsg("用户名或密码错误");
			}
			String userAccount = jsonObj.getString("userAccount");
			String userPassword = jsonObj.getString("userPassword");
			userPassword = MD5Util.createMD5(userPassword);
			User user = userService.checkUser(userAccount, userPassword);
			if (user != null && !StringUtils.isEmpty(user.getUserAccount())) {
				user.setLastLoginTime(new Date());
				user.setLastLoginIp(getIpAddr(request));
				userService.update(user);
				return getSuccessResultMsg(JSONObject.toJSONString(user));
			} else {
				return getErrorResultMsg("用户名或密码错误");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/user/insert", method = RequestMethod.POST)
	@ResponseBody
	public String userInsert(@RequestBody String content) {
		try {
			User t = JsonBeanUtil.stringToBean(User.class, content);
			t.setUserPassword(MD5Util.createMD5(t.getUserPassword()));
			userService.insert(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	@ResponseBody
	public String userDelete(@RequestBody String content) {
		try {
			User t = JsonBeanUtil.stringToBean(User.class, content);
			userService.delete(t.getUserId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	@ResponseBody
	public String userUpdate(@RequestBody String content) {
		try {
			User t = JsonBeanUtil.stringToBean(User.class, content);
			userService.update(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/user/queryListByPage", method = RequestMethod.POST)
	@ResponseBody
	public String userQuery(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			PaginationDto<User> paginationDto = userService.queryListByPage(page, pageSize, null);
			return getSuccessResultMsg(JSONObject.toJSONString(paginationDto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/userAddress/insert", method = RequestMethod.POST)
	@ResponseBody
	public String userAddressInsert(@RequestBody String content) {
		try {
			UserAddress t = JsonBeanUtil.stringToBean(UserAddress.class, content);
			userAddressService.insert(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/userAddress/delete", method = RequestMethod.POST)
	@ResponseBody
	public String userAddressDelete(@RequestBody String content) {
		try {
			UserAddress t = JsonBeanUtil.stringToBean(UserAddress.class, content);
			userAddressService.delete(t.getId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/userAddress/update", method = RequestMethod.POST)
	@ResponseBody
	public String userAddressUpdate(@RequestBody String content) {
		try {
			UserAddress t = JsonBeanUtil.stringToBean(UserAddress.class, content);
			userAddressService.update(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/userAddress/queryListByPage", method = RequestMethod.POST)
	@ResponseBody
	public String userAddressQuery(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			Long userId = jsonObj.getLong("userId");
			PaginationDto<UserAddress> paginationDto = userAddressService.queryListByPage(page, pageSize, null, userId);
			return getSuccessResultMsg(JSONObject.toJSONString(paginationDto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}
}
