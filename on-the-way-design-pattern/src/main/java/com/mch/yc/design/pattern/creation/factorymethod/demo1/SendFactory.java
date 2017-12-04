package com.mch.yc.design.pattern.creation.factorymethod.demo1;

/**
 * @author yc
 *
 */
public class SendFactory {

	public ISender produce(String type) {
		if ("mail".equals(type)) {
			return new MailSender();
		} else if ("sms".equals(type)) {
			return new SmsSender();
		} else {
			return null;
		}

	}

}
