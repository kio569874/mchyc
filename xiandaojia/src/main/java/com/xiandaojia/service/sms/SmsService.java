package com.xiandaojia.service.sms;


public interface SmsService {

	/**
	 * 获取验证码
	 * 
	 * @param appKey
	 * @param userId
	 * @return
	 */
	String getCheckCode(String appKey, String phoneNo, String checkCodeType);

	/**
	 * 发送短信
	 * 
	 * @param phone
	 * @param checkCodeType
	 * @return
	 */
	String sendSmsCheckCode(String phoneNo, String checkCode, String hour);

	/**
	 * 验证短信验证码
	 * 
	 * @param appKey
	 * @param userId
	 * @param smsToken
	 * @param checkCode
	 * @return
	 */
	String verifyCheckCode(String appKey, String phoneNo, String smsToken, String checkCode);

}
