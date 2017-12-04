package com.mch.yc.design.pattern.creation.factorymethod.demo3;

/**
 * 
 * @author yc
 *
 */
public class FactoryTest {

	public static void main(String[] args) {
		ISender iSender = SendFactory.produceMail();
		iSender.send();
	}
}
