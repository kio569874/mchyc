package com.xiandaojia.spi;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiandaojia.common.Contants;
import com.xiandaojia.common.domain.SmsConfig;
import com.xiandaojia.common.domain.SmsSendJnl;
import com.xiandaojia.common.enums.StatusEnum;
import com.xiandaojia.common.enums.SmsTypeEnum;
import com.xiandaojia.common.exception.SmsException;
import com.xiandaojia.spi.sms.ISmsHandle;

public class SmsServiceManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private List<SmsConfig> smsConfigList = new ArrayList<SmsConfig>();

	private final static SmsServiceManager instance = new SmsServiceManager();

	private SmsServiceManager() {

	}

	public static SmsServiceManager getInstance() {
		return instance;
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param SmsCheckCode
	 */
	public void sendSmsCheckCode(SmsSendJnl smsSendJnl, String checkCode,String hour) {
		try {
			SmsConfig smsConfig = getSmsConfig(SmsTypeEnum.YZM.getSmsType());
			if (smsConfig != null) {
				ISmsHandle ISmsHandler = getSmsImplClass(smsConfig.getImplClass());
				smsSendJnl.setSendSmsType(SmsTypeEnum.YZM.getSmsType());
				ISmsHandler.sendSmsCheckCode(smsConfig, smsSendJnl, checkCode,hour);
			} else {
				smsSendJnl.setSendReturnCode(Contants.RET_CODE_FAIL);
				smsSendJnl.setSendReturnMessage("未获取到短信平台信息");
			}
		} catch (SmsException e) {
			smsSendJnl.setSendReturnCode(Contants.RET_CODE_FAIL);
			smsSendJnl.setSendReturnMessage("发送短信验证码异常");
		}
	}

	/**
	 * 获取实现类
	 * 
	 * @param smsImpleClass
	 * @return
	 */
	private ISmsHandle getSmsImplClass(String smsImpleClass) {
		ISmsHandle iSmsHandler = null;
		if (iSmsHandler == null) {
			try {
				if (iSmsHandler == null) {
					iSmsHandler = (ISmsHandle) Class.forName(smsImpleClass).newInstance();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return iSmsHandler;
	}

	private SmsConfig getSmsConfig(String smsType) {
		if (smsConfigList.size() == 0) {
			return null;
		}
		if (smsConfigList != null && smsConfigList.size() > 0) {
			for (SmsConfig smsConfig : smsConfigList) {
				if (smsType.equals(smsConfig.getSmsType())
						&& StatusEnum.START.getStatus().equals(smsConfig.getStatus())) {
					return smsConfig;
				}
			}
		}
		return null;
	}

	public List<SmsConfig> getSmsConfigList() {
		return smsConfigList;
	}

	public void setSmsConfigList(List<SmsConfig> smsConfigList) {
		this.smsConfigList = smsConfigList;
	}

	public boolean isSmsConfigEmpty() {
		return smsConfigList == null || smsConfigList.size() == 0;
	}

}
