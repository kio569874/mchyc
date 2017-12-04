package com.mch.yc.design.pattern.creation.builder.demo3;

/**
 * 
 * @author yc
 *
 */
public class GoodbyeBuilder extends Builder {
	
	public GoodbyeBuilder() {
		msg = new GoodbyeMessage();
	}

	@Override
	public void buildBody() {
		msg.setBody("欢送内容");
	}

	@Override
	public void buildSubject() {
		msg.setSubject("欢送标题");
	}
}
