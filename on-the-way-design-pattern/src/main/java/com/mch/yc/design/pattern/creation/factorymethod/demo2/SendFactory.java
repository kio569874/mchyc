package com.mch.yc.design.pattern.creation.factorymethod.demo2;

/**
 * 
 * @author yc
 *
 */
public class SendFactory {

	public ISender produceMail() {
		return new MailSender();

	}

	public ISender produceSms() {
		return new SmsSender();
	}

}
