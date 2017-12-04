package com.mch.yc.design.pattern.creation.abstractfactory;

/**
 * 
 * 
 * @author yc
 *
 */
public class SmsSendFactory implements Provider{

	@Override
	public ISender produce() {
		return new SmsSender();
	}


}
