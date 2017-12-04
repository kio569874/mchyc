package com.mch.yc.design.pattern.creation.factorymethod.demo2;

/**
 * 
 * @author yc
 *
 */
public class SmsSender implements ISender {

	@Override
	public void send() {
		System.out.println("send sms");
	}

}
