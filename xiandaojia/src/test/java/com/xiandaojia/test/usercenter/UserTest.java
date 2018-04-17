package com.xiandaojia.test.usercenter;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.test.SendHttp;

public class UserTest {
	public static void main(String[] args) {

		 insert();
		 queryListByPage();

		// update();
		// queryListByPage();
		// delete();
		// queryListByPage();
		login();

	}

	private static void login() {
		JSONObject json = new JSONObject();
		json.put("userAccount", "yangchao");
		json.put("userPassword", "123456");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/user/login",
				json.toString());
		System.out.println(re);
	}

	private static void insert() {
		JSONObject json = new JSONObject();
		json.put("userAccount", "yangchao");
		json.put("userPassword", "123456");
		json.put("userName", "杨超");
		json.put("userPhone", "17802141727");
		json.put("userMemberName", "kio569874");
		json.put("userStatus", "0");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/user/insert",
				json.toString());
		System.out.println(re);
	}

	private static void update() {
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		json.put("userAccount", "yangchao");
		json.put("userPassword", "123456");
		json.put("userName", "杨超");
		json.put("userPhone", "17802141727");
		json.put("userMemberName", "huiyuan001");
		json.put("userStatus", "0");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/user/update",
				json.toString());
		System.out.println(re);
	}

	private static void delete() {
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/user/delete",
				json.toString());
		System.out.println(re);
	}

	private static void queryListByPage() {
		JSONObject json = new JSONObject();
		json.put("page", 1);
		json.put("pageSize", 10);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/user/queryListByPage",
				json.toString());
		System.out.println(re);
	}
}
