package com.xiandaojia.test.usercenter;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.test.SendHttp;

public class SystemUserTest {
	public static void main(String[] args) {
		login();
//		insert();
//		queryListByPage();
//		update();
//		queryListByPage();
//		delete();
//		queryListByPage();

	}

	private static void login() {
		JSONObject json = new JSONObject();
		json.put("userCode", "yangchao");
		json.put("userPassword", "123456");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/systemUser/login",
				json.toString());
		System.out.println(re);
	}
	private static void insert() {
		JSONObject json = new JSONObject();
		json.put("userCode", "yangchao");
		json.put("userPassword", "123456");
		json.put("userName", "杨超");
		json.put("userPhone", "17802141727");
		json.put("userLevel", "P1");
		json.put("userPosition", "码农");
		json.put("userStatus", "0");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/systemUser/insert",
				json.toString());
		System.out.println(re);
	}

	private static void update() {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		json.put("userCode", "yangchao");
		json.put("userPassword", "123456");
		json.put("userName", "杨超222");
		json.put("userPhone", "17802141727");
		json.put("userLevel", "P1");
		json.put("userPosition", "码农111");
		json.put("userStatus", "0");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/systemUser/update",
				json.toString());
		System.out.println(re);
	}

	private static void delete() {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/systemUser/delete",
				json.toString());
		System.out.println(re);
	}

	private static void queryListByPage() {
		JSONObject json = new JSONObject();
		json.put("page", 1);
		json.put("pageSize", 10);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/systemUser/queryListByPage",
				json.toString());
		System.out.println(re);
	}
}
