package com.mch.yc.design.pattern.creation.factorymethod.demo1;

/**
 * @author yc
 *
 */
public class FactoryTest {

	public static void main(String[] args) {
		SendFactory sendFactory = new SendFactory();
		ISender iSender = sendFactory.produce("mail");
		iSender.send();
	}
}
