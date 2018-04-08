package com.xiandaojia.test.param;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.test.SendHttp;

public class CityAreaTest {

	public static void main(String[] args) {
		queryListTree();
		query();
	}

	private static void insert() {
		JSONObject json = new JSONObject();
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/param/cityArea/insert",
				json.toString());
		System.out.println(re);
	}

	private static void update() {
		JSONObject json = new JSONObject();
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/param/cityArea/update",
				json.toString());
		System.out.println(re);
	}

	private static void delete() {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/param/cityArea/delete",
				json.toString());
		System.out.println(re);
	}

	private static void query() {
		JSONObject json = new JSONObject();
		json.put("parentid", "0");
//		json.put("plevel", "1");
//		json.put("plevel", "2");
//		json.put("plevel", "3");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/param/cityArea/query",
				json.toString());
		System.out.println(re);
	}

	private static void queryListTree() {
		JSONObject json = new JSONObject();
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/param/cityArea/queryListTree",
				json.toString());
		System.out.println(re);
	}
}
