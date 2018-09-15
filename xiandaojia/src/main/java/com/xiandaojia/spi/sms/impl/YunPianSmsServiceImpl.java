
package com.xiandaojia.spi.sms.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.domain.SmsConfig;
import com.xiandaojia.common.domain.SmsSendJnl;
import com.xiandaojia.common.exception.SmsException;
import com.xiandaojia.spi.sms.ISmsHandle;
import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;


/**
 * 云片网短信服务实现
 * 
 * @author yc
 * @param
 *
 */

public class YunPianSmsServiceImpl extends AbstractSmsHandler implements ISmsHandle {

	@Override
	public void sendSmsCheckCode(SmsConfig smsConfig, SmsSendJnl smsSendJnl,String checkCode, String hour) throws SmsException {
		String apikey = smsConfig.getApikey();
		String mouldContext = smsConfig.getMouldContext();
		mouldContext = mouldContext.replace("#code", checkCode);
		mouldContext = mouldContext.replace("#HOUR", hour);
		smsSendJnl.setSmsContext(mouldContext);
		smsSendJnl.setSendTime(new Date());

		// TODO 可以改成这样系统的post请求发送，不直接使用云片网的api
		YunpianRestClient client = new YunpianRestClient(apikey);// 用apikey生成client,可作为全局静态变量
		SmsOperator smsOperator = client.getSmsOperator();// 获取所需操作类
		ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend(smsSendJnl.getSendPhone(), mouldContext);// 发送短信,ResultDO<?>.isSuccess()判断是否成功
		logger.info(JSONObject.toJSONString(result));
		this.buildResultData(result, smsSendJnl);
	}

	public void buildResultData(ResultDO<SendSingleSmsInfo> result, SmsSendJnl smsSendJnl) {
		if (result.isSuccess()) {
			BigDecimal snedFee = new BigDecimal(result.getData().getFee() + "");
			smsSendJnl.setSendReturnCode(result.getData().getCode() + "");
			smsSendJnl.setSendReturnMessage(result.getData().getMsg() + "");
			smsSendJnl.setSendCount(result.getData().getCount());
			smsSendJnl.setSendFee(snedFee);
			smsSendJnl.setFeeUnit(result.getData().getUnit());
			smsSendJnl.setSendId(result.getData().getSid());
		} else {
			String failMsg = result.getE().getMessage();
			JSONObject jsonObj = JSONObject.parseObject(failMsg);
			Map<String, Object> map = jsonObj;
			String code = map.get("code") == null ? "" : map.get("code").toString();
			String message = map.get("detail") == null ? "" : map.get("detail").toString();
			String httpStatusCode = map.get("http_status_code") == null ? "" : map.get("http_status_code").toString();
			smsSendJnl.setSendReturnCode(code);
			smsSendJnl.setSendReturnMessage(message);
		}

	}
}

