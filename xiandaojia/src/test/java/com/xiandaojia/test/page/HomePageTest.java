package com.xiandaojia.test.page;

import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.enums.NoticeTypeEnum;
import com.xiandaojia.common.enums.StatusEnum;
import com.xiandaojia.test.SendHttp;

public class HomePageTest {

	public static void main(String[] args) {
		// noticeInfoInsert();
		// noticeInfoQueryListByPage();
		// noticeInfoUpdate();
		// noticeInfoQueryListByPage();
		// noticeInfoDelete();
		// noticeInfoQueryListByPage();

		homePageImageInsert();
		homePageImageQueryListByPage();
		// homePageImageUpdate();
		// homePageImageQueryListByPage();
		// homePageImageDelete();
		// homePageImageQueryListByPage();

	}

	private static void noticeInfoInsert() {
		JSONObject json = new JSONObject();
		json.put("noticeTitle", "鲜到家上线了");
		json.put("noticeContent", "鲜到家上线了");
		json.put("noticeSeqno", 0);
		json.put("noticeType", NoticeTypeEnum.NO_JUMP.getType());
		json.put("noticeUrl", "");
		json.put("status", StatusEnum.START.getStatus());
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/homePage/noticeInfo/insert",
				json.toString());
		System.out.println(re);
	}

	private static void noticeInfoUpdate() {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		json.put("noticeTitle", "鲜到家上线了222");
		json.put("noticeContent", "鲜到家上线了111");
		json.put("noticeSeqno", 0);
		json.put("noticeType", NoticeTypeEnum.NO_JUMP.getType());
		json.put("noticeUrl", "");
		json.put("status", StatusEnum.START.getStatus());
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/homePage/noticeInfo/update",
				json.toString());
		System.out.println(re);
	}

	private static void noticeInfoDelete() {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/homePage/noticeInfo/delete",
				json.toString());
		System.out.println(re);
	}

	private static void noticeInfoQueryListByPage() {
		JSONObject json = new JSONObject();
		json.put("page", 1);
		json.put("pageSize", 10);
		System.out.println(json);
		String re = SendHttp.getInstance()
				.sendPost("http://127.0.0.1:8999/xiandaojia/homePage/noticeInfo/queryListByPage", json.toString());
		System.out.println(re);
	}

	private static void homePageImageInsert() {
		JSONObject json = new JSONObject();
		json.put("contentName", "我是一张轮播图");
		json.put("contentDesc", "我是轮播图的备注啊");
		json.put("imageSeqno", 0);
		json.put("toUrl", null);
		json.put("status", StatusEnum.START.getStatus());
		json.put("operator", "yangchao");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/homePage/homePageImage/insert",
				json.toString());
		System.out.println(re);
	}

	private static void homePageImageUpdate() {
		JSONObject json = new JSONObject();
		json.put("contentName", "我是一张轮播图111");
		json.put("contentDesc", "我是轮播图的备注啊111");
		json.put("imageSeqno", 0);
		json.put("toUrl", null);
		json.put("status", StatusEnum.START.getStatus());
		json.put("operator", "yangchao");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/homePage/homePageImage/update",
				json.toString());
		System.out.println(re);
	}

	private static void homePageImageDelete() {
		JSONObject json = new JSONObject();
		json.put("id", 1);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/homePage/homePageImage/delete",
				json.toString());
		System.out.println(re);
	}

	private static void homePageImageQueryListByPage() {
		JSONObject json = new JSONObject();
		json.put("page", 1);
		json.put("pageSize", 10);
		System.out.println(json);
		String re = SendHttp.getInstance()
				.sendPost("http://127.0.0.1:8999/xiandaojia/homePage/homePageImage/queryListByPage", json.toString());
		System.out.println(re);
	}
}
