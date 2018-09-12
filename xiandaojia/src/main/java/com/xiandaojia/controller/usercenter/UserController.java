package com.xiandaojia.controller.usercenter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.auth.bean.UserDetail;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ShoppingCart;
import com.xiandaojia.common.domain.SystemUser;
import com.xiandaojia.common.domain.User;
import com.xiandaojia.common.domain.UserAddress;
import com.xiandaojia.common.utils.JsonBeanUtil;
import com.xiandaojia.common.utils.JwtUtil;
import com.xiandaojia.common.utils.MD5Util;
import com.xiandaojia.controller.BaseController;
import com.xiandaojia.service.usercenter.IShoppingCartService;
import com.xiandaojia.service.usercenter.ISystemUserService;
import com.xiandaojia.service.usercenter.IUserAddressService;
import com.xiandaojia.service.usercenter.IUserService;

@RestController
@RequestMapping("user")
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
				return getSuccessResultMsg(JSONObject.toJSONString(createTokenResult(systemUser)));
			} else {
				return getErrorResultMsg("用户名或密码错误");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}
	
	/**
	 * 封装带token的响应结果
	 * @param systemUser
	 * @return
	 */
	private Map<String,Object> createTokenResult(SystemUser systemUser) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("user", systemUser);
		systemUser.setUserPassword(null);
		result.put("token", JwtUtil.sign(UserDetail.from(systemUser), JwtUtil.EXP_TIME));
		return result;
	}

	@RequestMapping(value = "/systemUser/insert", method = RequestMethod.POST)
	@ResponseBody
	public String insert(@RequestBody String content) {
		try {
			//SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, content);
			//systemUser.setUserPassword(MD5Util.createMD5(systemUser.getUserPassword()));
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, data.toJSONString());
			systemUser.setUserPassword(MD5Util.createMD5("xdj@2018"));
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
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, data.toJSONString());
			//systemUserService.delete(systemUser.getId());
			systemUser.setDelFlag("1");
			systemUserService.update(systemUser);
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
			//SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, content);
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, data.toJSONString());
			systemUserService.update(systemUser);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}
/*
*
* 密码修改
* */
	@RequestMapping(value = "/systemUser/passWordModify", method = RequestMethod.POST)
	@ResponseBody
	public String passWordModify(@RequestBody String content) {
		try {
			//SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, content);
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			Long id = Long.valueOf(data.getString("id"));
			String passWord = data.getString("userPassword");
			String passWordAgain = data.getString("usePasswordAgain");
			if(!passWord.equals(passWordAgain)){
				logger.error("两次密码不一致");
				return getErrorResultMsg("两次密码不一致");
			}
			SystemUser systemUser = new SystemUser();
			systemUser.setId(id);
			systemUser.setUserPassword(passWord);
			systemUserService.update(systemUser);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}
	@RequestMapping(value = "/systemUser/passWordReset", method = RequestMethod.POST)
	@ResponseBody
	public String passWordReset(@RequestBody String content) {
		try {
			//SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, content);
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			Long id = Long.valueOf(data.getString("id"));
			String passWord = MD5Util.createMD5("xdj@2018");
			SystemUser systemUser = new SystemUser();
			systemUser.setId(id);
			systemUser.setUserPassword(passWord);
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
			return getSuccessPageResultMsg(paginationDto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}
	@RequestMapping(value = "/systemUser/queryListById", method = RequestMethod.POST)
	@ResponseBody
	public String queryListById(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			SystemUser systemUser = JsonBeanUtil.stringToBean(SystemUser.class, data.toJSONString());
			SystemUser User = systemUserService.selectUserById(systemUser.getId());
			return getSuccessResultMsg(JSONObject.toJSONString(User));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}



	@RequestMapping(value = "/shoppingCart/insert", method = RequestMethod.POST)
	@ResponseBody
	public String shoppingCartInsert(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			ShoppingCart shoppingCart = JsonBeanUtil.stringToBean(ShoppingCart.class, data.toJSONString());
			//传参：ProductId productNum
			shoppingCart.setUserId(Long.valueOf(4));//后台获取
			shoppingCart.setCreateTime(new Date());
			shoppingCart.setIsSelect("0");//默认未选中

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
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			ShoppingCart shoppingCart = JsonBeanUtil.stringToBean(ShoppingCart.class, data.toJSONString());
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
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			ShoppingCart shoppingCart = JsonBeanUtil.stringToBean(ShoppingCart.class, data.toJSONString());
			shoppingCartService.update(shoppingCart);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/shoppingCart/queryList", method = RequestMethod.POST)
	@ResponseBody
	public String shoppingCartQuery(@RequestBody String content) {
		try {
			/*JSONObject jsonObj = JSONObject.parseObject(content);
			Long userId = jsonObj.getLong("userId");*///LWQ 改变USERID通过后台获取
			String result = shoppingCartService.queryListByUserId(Long.valueOf(4));
			return getSuccessResultMsg(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/shoppingCart/selectAll", method = RequestMethod.POST)
	@ResponseBody
	public String shoppingCartselectAll(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			ShoppingCart shoppingCart = JsonBeanUtil.stringToBean(ShoppingCart.class, data.toJSONString());

			shoppingCart.setUserId(Long.valueOf(4));//----------
			shoppingCart.setCreateTime(new Date());
			shoppingCartService.upadteStateAll(shoppingCart);
			return getSuccessResultMsg();
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
			return getSuccessPageResultMsg(paginationDto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/userAddress/insert", method = RequestMethod.POST)
	@ResponseBody
	public String userAddressInsert(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			//ShoppingCart shoppingCart = JsonBeanUtil.stringToBean(ShoppingCart.class, data.toJSONString());
			UserAddress t = JsonBeanUtil.stringToBean(UserAddress.class, data.toJSONString());
			logger.info("data.toJSONString()："+data.toJSONString());
			t.setUserId("4");//-----
			t.setCreateTime(new Date());
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
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			//ShoppingCart shoppingCart = JsonBeanUtil.stringToBean(ShoppingCart.class, data.toJSONString());
			UserAddress t = JsonBeanUtil.stringToBean(UserAddress.class, data.toJSONString());
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
			JSONObject jsonObj = JSONObject.parseObject(content);
			Map<String, Object> paramMap = jsonObj;
			JSONObject data =  (JSONObject)paramMap.get("data");
			//ShoppingCart shoppingCart = JsonBeanUtil.stringToBean(ShoppingCart.class, data.toJSONString());
			UserAddress t = JsonBeanUtil.stringToBean(UserAddress.class, data.toJSONString());
			userAddressService.update(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/userAddress/queryList", method = RequestMethod.POST)
	@ResponseBody
	public String userAddressQuery(@RequestBody String content) {
		try {
			/*JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			Long userId = jsonObj.getLong("userId");*/
			//PaginationDto<UserAddress> paginationDto = userAddressService.queryListByPage(1, 50, null, Long.valueOf(4));
			String result = userAddressService.queryList(Long.valueOf(4));
			return getSuccessResultMsg(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}
}
