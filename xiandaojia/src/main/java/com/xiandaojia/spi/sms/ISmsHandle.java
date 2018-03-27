package com.xiandaojia.spi.sms;

import com.xiandaojia.common.domain.SmsConfig;
import com.xiandaojia.common.domain.SmsSendJnl;
import com.xiandaojia.common.exception.SmsException;

public interface ISmsHandle {

	void sendSmsCheckCode(SmsConfig smsConfig, SmsSendJnl smsSendJnl,String checkCode,String hour) throws SmsException;


}
