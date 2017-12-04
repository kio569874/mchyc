package com.mch.yc.design.pattern.creation.abstractfactory;

/**
 * 
 * @author yc
 *
 */
public class FactoryTest {

	public static void main(String[] args) {
		Provider provider = new MailSendFactory();
		ISender iSender = provider.produce();
		iSender.send();
	}
}
