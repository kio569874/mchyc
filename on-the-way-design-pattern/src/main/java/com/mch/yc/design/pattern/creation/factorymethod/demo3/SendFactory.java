package com.mch.yc.design.pattern.creation.factorymethod.demo3;

/**
 * 
 * 
 * @author yc
 *
 */
public class SendFactory {

	public static ISender produceMail() {
		return new MailSender();

	}

	public static ISender produceSms() {
		return new SmsSender();
	}

}
