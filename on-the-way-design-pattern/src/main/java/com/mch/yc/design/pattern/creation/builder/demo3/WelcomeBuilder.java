package com.mch.yc.design.pattern.creation.builder.demo3;

/**
 * 
 * @author yc
 *
 */
public class WelcomeBuilder extends Builder {

	public WelcomeBuilder() {
		msg = new WelcomeMessage();
	}

	@Override
	public void buildBody() {
		msg.setBody("欢迎内容");
	}

	@Override
	public void buildSubject() {
		msg.setSubject("欢迎标题");
	}
}
