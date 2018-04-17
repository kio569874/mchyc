package com.xiandaojia.test.usercenter;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.test.SendHttp;

public class ShoppingCartTest {

	public static void main(String[] args) {
		insert();
		queryListByPage();
		// update();
		// queryListByPage();
		// delete();
		// queryListByPage();
	}

	private static void insert() {
		JSONObject json = new JSONObject();
		json.put("userId", 3);
		json.put("productId", 1);
		json.put("productNum", 2);
		json.put("isSelect", "0");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/shoppingCart/insert",
				json.toString());
		System.out.println(re);
	}

	private static void update() {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		json.put("productId", 1);
		json.put("productNum", 1);
		json.put("isSelect", "1");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/shoppingCart/update",
				json.toString());
		System.out.println(re);
	}

	private static void delete() {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/shoppingCart/delete",
				json.toString());
		System.out.println(re);
	}

	private static void queryListByPage() {
		JSONObject json = new JSONObject();
		json.put("page", 1);
		json.put("pageSize", 10);
		json.put("userId", 3);
		System.out.println(json);
		String re = SendHttp.getInstance()
				.sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/shoppingCart/queryListByPage", json.toString());
		System.out.println(re);
	}
}
