package com.xiandaojia.test.usercenter;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.test.SendHttp;

public class UserAddressTest {

	public static void main(String[] args) {
		// insert();
		// queryListByPage();
		// update();
		// queryListByPage();
		delete();
		queryListByPage();
	}

	private static void insert() {
		JSONObject json = new JSONObject();
		json.put("addressProvince", "贵州省");
		json.put("addressCity", "铜仁市");
		json.put("addressArea", "江口县");
		json.put("addressText", "太平镇梵净山");
		json.put("addressDefault", true);
		json.put("userId", 3);
		json.put("userName", "杨一");
		json.put("userPhone", "17802141727");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/userAddress/insert",
				json.toString());
		System.out.println(re);
	}

	private static void update() {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		json.put("addressProvince", "贵州省");
		json.put("addressCity", "铜仁市");
		json.put("addressArea", "江口县");
		json.put("addressText", "太平镇梵净山111");
		json.put("addressDefault", false);
		json.put("userId", 3);
		json.put("userName", "杨一");
		json.put("userPhone", "17802141727");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/userAddress/update",
				json.toString());
		System.out.println(re);
	}

	private static void delete() {
		JSONObject json = new JSONObject();
		json.put("id", 2);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/userAddress/delete",
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
				.sendPost("http://127.0.0.1:8999/xiandaojia/userCenter/userAddress/queryListByPage", json.toString());
		System.out.println(re);
	}
}
