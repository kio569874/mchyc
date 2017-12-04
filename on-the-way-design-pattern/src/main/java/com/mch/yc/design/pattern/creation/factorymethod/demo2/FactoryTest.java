package com.mch.yc.design.pattern.creation.factorymethod.demo2;

/**
 * 
 * @author yc
 *
 */
public class FactoryTest {

	public static void main(String[] args) {
		SendFactory sendFactory = new SendFactory();
		ISender iSender = sendFactory.produceMail();
		iSender.send();
	}
}
