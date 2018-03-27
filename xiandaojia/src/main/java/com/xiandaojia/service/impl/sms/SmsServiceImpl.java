package com.xiandaojia.service.impl.sms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.Contants;
import com.xiandaojia.common.SmsContants;
import com.xiandaojia.common.domain.CheckCodeConfig;
import com.xiandaojia.common.domain.SmsCheckCodeJnl;
import com.xiandaojia.common.domain.SmsConfig;
import com.xiandaojia.common.domain.SmsSendJnl;
import com.xiandaojia.common.enums.CheckResultEnum;
import com.xiandaojia.common.utils.CreateIdUtil;
import com.xiandaojia.common.utils.SmsCheckCodeUtil;
import com.xiandaojia.mapper.sms.CheckCodeConfigMapper;
import com.xiandaojia.mapper.sms.SmsCheckCodeJnlMapper;
import com.xiandaojia.mapper.sms.SmsConfigMapper;
import com.xiandaojia.mapper.sms.SmsSendJnlMapper;
import com.xiandaojia.service.impl.AbstractService;
import com.xiandaojia.service.sms.SmsService;
import com.xiandaojia.spi.SmsServiceManager;

/**
 * 短信服务
 * 
 * @author mchyc
 *
 */
@Service("smsService")
public class SmsServiceImpl extends AbstractService implements SmsService {

	@Resource
	private SmsConfigMapper smsConfigMapper;
	@Resource
	private SmsCheckCodeJnlMapper smsCheckCodeJnlMapper;
	@Resource
	private CheckCodeConfigMapper checkCodeConfigMapper;
	@Resource
	private SmsSendJnlMapper smsSendJnlMapper;

	@Override
	public String getCheckCode(String appKey, String phoneNo, String checkcodeType) {
		// 校验appkey是否正确,同时返回配置的时间
		List<CheckCodeConfig> checkCodeConfigList = checkCodeConfigMapper.queryCheckCodeConfigList(appKey);
		if (checkCodeConfigList == null || checkCodeConfigList.size() == 0) {
			return null;
		}
		CheckCodeConfig checkCodeConfig = checkCodeConfigList.get(0);
		String checkCode = SmsCheckCodeUtil.productCheckCode(1, 999999);
		String token = SmsCheckCodeUtil.productToken();
		SmsCheckCodeJnl smsCheckCodeJnl = new SmsCheckCodeJnl();
		smsCheckCodeJnl.setPhoneNo(phoneNo);
		smsCheckCodeJnl.setCheckCode(checkCode);
		smsCheckCodeJnl.setSmsToken(token);
		smsCheckCodeJnl.setCheckcodeType(checkcodeType);
		smsCheckCodeJnl.setAppKey(appKey);
		smsCheckCodeJnl.setValidDuration(checkCodeConfig.getValidDuration());
		smsCheckCodeJnl.setCreateTime(new Date());
		smsCheckCodeJnl.setCheckErrorNum(0);
		smsCheckCodeJnlMapper.insert(smsCheckCodeJnl);
		return JSONObject.toJSONString(smsCheckCodeJnl);
	}

	@Override
	public String sendSmsCheckCode(String phone, String checkCode, String hour) {
		if (SmsServiceManager.getInstance().isSmsConfigEmpty()) {
			List<SmsConfig> smsConfigList = this.smsConfigMapper.querySmsConfigList();
			SmsServiceManager.getInstance().setSmsConfigList(smsConfigList);
		}
		if (hour == null || "".equals(hour)) {
			hour = "15分钟";
		}
		SmsSendJnl smsSendJnl = new SmsSendJnl();
		smsSendJnl.setSmsSendId(CreateIdUtil.createId());
		smsSendJnl.setSendPhone(phone);
		smsSendJnl.setCreateTime(new Date());
		SmsServiceManager.getInstance().sendSmsCheckCode(smsSendJnl, checkCode,hour);
		smsSendJnl.setUpdateTime(new Date());
		smsSendJnlMapper.insert(smsSendJnl);
		return JSONObject.toJSONString(smsSendJnl);
	}

	@Override
	public String verifyCheckCode(String appKey, String phoneNo, String smsToken, String checkCode) {
		JSONObject resultJson = new JSONObject();
		List<CheckCodeConfig> checkCodeConfigList = checkCodeConfigMapper.queryCheckCodeConfigList(appKey);
		if (checkCodeConfigList == null || checkCodeConfigList.size() == 0) {
			resultJson.put(Contants.RET_CODE, SmsContants.E_SMS_MSG_202001);
			resultJson.put(Contants.RET_MSG, SmsContants.E_SMS_MSG_202001);
			return resultJson.toJSONString();
		}

		List<SmsCheckCodeJnl> smsCheckCodeJnlList = smsCheckCodeJnlMapper.querySmsCheckCodeList(appKey, phoneNo,
				smsToken);
		if (smsCheckCodeJnlList == null || smsCheckCodeJnlList.size() == 0 || smsCheckCodeJnlList.size() > 1) {
			resultJson.put(Contants.RET_CODE, SmsContants.E_SMS_CODE_202003);
			resultJson.put(Contants.RET_MSG, SmsContants.E_SMS_MSG_202003);
			return resultJson.toJSONString();
		}
		SmsCheckCodeJnl smsCheckCodeJnl = smsCheckCodeJnlList.get(0);
		// 1、校验验证码是否正确
		if (!checkCode.equals(smsCheckCodeJnl.getCheckCode())) {
			// 验证码错误
			smsCheckCodeJnl.setCheckErrorNum(smsCheckCodeJnl.getCheckErrorNum() + 1);
			smsCheckCodeJnl.setCheckResult(CheckResultEnum.FAIL.getStatus());
			this.updateSmsCheckCodeJnl(smsCheckCodeJnl);
			return buildResult(resultJson, SmsContants.E_SMS_CODE_202003, SmsContants.E_SMS_MSG_202003);
		}
		// 2、校验验证码有效时间
		int validDuration = smsCheckCodeJnl.getValidDuration();
		Date createTime = smsCheckCodeJnl.getCreateTime();
		Date nowDate = new Date();
		long diff = nowDate.getTime() - createTime.getTime();
		if ((diff / 1000) > validDuration) {
			// 验证码已过有效期
			smsCheckCodeJnl.setCheckErrorNum(smsCheckCodeJnl.getCheckErrorNum() + 1);
			smsCheckCodeJnl.setCheckResult(CheckResultEnum.FAIL.getStatus());
			this.updateSmsCheckCodeJnl(smsCheckCodeJnl);
			return buildResult(resultJson, SmsContants.E_SMS_CODE_202002, SmsContants.E_SMS_MSG_202003);
		}

		// 验证通过
		smsCheckCodeJnl.setCheckErrorNum(0);
		smsCheckCodeJnl.setCheckResult(CheckResultEnum.PASS.getStatus());
		this.updateSmsCheckCodeJnl(smsCheckCodeJnl);
		return resultJson.toString();
	}

	/**
	 * 修改短信验证码流水信息
	 * 
	 * @param smsCheckCodeJnl
	 */
	private void updateSmsCheckCodeJnl(SmsCheckCodeJnl smsCheckCodeJnl) {
		// 更新验证码流水表信息
		smsCheckCodeJnl.setUpdateTime(new Date());
		smsCheckCodeJnlMapper.updateByPrimaryKey(smsCheckCodeJnl);
	}

	/**
	 * 构建结果信息
	 * 
	 * @param resultJson
	 * @param retCode
	 * @param retMsg
	 * @return
	 */
	private String buildResult(JSONObject resultJson, String retCode, String retMsg) {
		resultJson.put(Contants.RET_CODE, retCode);
		resultJson.put(Contants.RET_MSG, retMsg);
		return resultJson.toJSONString();
	}

}
