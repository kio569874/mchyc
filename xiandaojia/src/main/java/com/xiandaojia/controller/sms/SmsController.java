package com.xiandaojia.controller.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.Contants;
import com.xiandaojia.common.enums.CheckCodeEnum;
import com.xiandaojia.controller.BaseController;
import com.xiandaojia.service.sms.SmsService;

/**
 * 短信服务
 * 
 * @author mchyc
 *
 */
@RestController
@RequestMapping("sms")
public class SmsController extends BaseController {

	@Autowired
	private SmsService smsService;

	@RequestMapping(value = "/getCheckCode", method = RequestMethod.POST)
	@ResponseBody
	public String getCheckCode(@RequestBody String content) {
		try {
			JSONObject jsonObj = getDataJSONObject(content);
			if (!jsonObj.containsKey("phoneNo") || jsonObj.get("phoneNo") == null
					|| "".equals(jsonObj.get("phoneNo"))) {
				return getErrorResultMsg("手机号码不能为空");
			}
			String phoneNo = jsonObj.getString("phoneNo");
			String appKey = jsonObj.getString("appKey");
			String checkCodeType = jsonObj.getString("checkcodeType");
			String result = smsService.getCheckCode(appKey, phoneNo,
					CheckCodeEnum.valueOf(checkCodeType).getCheckCodeType());
			if (result != null && !"".equals(result)) {
				return getSuccessResultMsg(JSONObject.parse(result));
			} else {
				return getErrorResultMsg("获取验证码失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/sendSmsCheckCode", method = RequestMethod.POST)
	@ResponseBody
	public String sendSmsCheckCode(@RequestBody String content) {
		try {
			JSONObject jsonObj = getDataJSONObject(content);
			if (!jsonObj.containsKey("phoneNo") || jsonObj.get("phoneNo") == null
					|| "".equals(jsonObj.get("phoneNo"))) {
				return getErrorResultMsg("手机号码不能为空");
			}
			String phoneNo = jsonObj.getString("phoneNo");
			String checkCode = jsonObj.getString("checkCode");
			String hour = jsonObj.getString("hour");
			String result = smsService.sendSmsCheckCode(phoneNo, checkCode, hour);
			return getSuccessResultMsg(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/verifyCheckCode", method = RequestMethod.POST)
	@ResponseBody
	public String verifyCheckCode(@RequestBody String content) {
		try {
			JSONObject jsonObj = getDataJSONObject(content);
			if (!jsonObj.containsKey("phoneNo") || jsonObj.get("phoneNo") == null
					|| "".equals(jsonObj.get("phoneNo"))) {
				return getErrorResultMsg("手机号码不能为空");
			}
			String phoneNo = jsonObj.getString("phoneNo");
			String appKey = jsonObj.getString("appKey");
			String checkCode = jsonObj.getString("checkCode");
			String smsToken = jsonObj.getString("smsToken");
			String re = smsService.verifyCheckCode(appKey, phoneNo, smsToken, checkCode);
			JSONObject reJsonObj = JSONObject.parseObject(re);
			if(reJsonObj.containsKey(Contants.RET_MSG)) {
				return getErrorResultMsg(reJsonObj.getString(Contants.RET_MSG));
			}else {
				return getSuccessResultMsg(re);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}
}
