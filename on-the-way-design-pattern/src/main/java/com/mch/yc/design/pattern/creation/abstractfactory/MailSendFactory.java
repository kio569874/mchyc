package com.mch.yc.design.pattern.creation.abstractfactory;

/**
 * 
 * 
 * @author yc
 *
 */
public class MailSendFactory implements Provider{

	@Override
	public ISender produce() {
		return new MailSender();
	}


}
