package com.xiandaojia.auth.bean;

import com.xiandaojia.common.domain.SystemUser;

public class UserDetail{
	
	Long userId;
	String username;
	
	public static UserDetail from(SystemUser systemUser) {
		UserDetail user = new UserDetail();
		user.setUserId(systemUser.getId());
		user.setUsername(systemUser.getUserName());
		return user;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
