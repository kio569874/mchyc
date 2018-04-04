package com.xiandaojia.test.sms;

import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.test.SendHttp;

public class SmsTest {
	public static void main(String[] args) {
		sendSms();
	}

	private static void sendSms() {
		String phoneNo = "17802141727";
		String rec = getCheckCode(phoneNo);
		JSONObject json = JSONObject.parseObject(rec);
		JSONObject dataJson = json.getJSONObject("data");
		String checkCode = dataJson.getString("checkCode");
		String smsToken = dataJson.getString("smsToken");
		sendSmsCheckCode(phoneNo, checkCode);
		verifyCheckCode(phoneNo, smsToken, checkCode);
	}

	private static String getCheckCode(String phoneNo) {
		JSONObject json = new JSONObject();
		json.put("phoneNo", phoneNo);
		json.put("appKey", "1001");
		json.put("checkcodeType", "USER_REGISTER");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/sms/getCheckCode",
				json.toString());
		System.out.println(re);
		return re;
	}

	private static void sendSmsCheckCode(String phoneNo, String checkCode) {
		JSONObject json = new JSONObject();
		json.put("phoneNo", phoneNo);
		json.put("checkCode", checkCode);
		json.put("hour", "3分钟");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/sms/sendSmsCheckCode",
				json.toString());
		System.out.println(re);
	}

	private static void verifyCheckCode(String phoneNo, String smsToken, String checkCode) {
		JSONObject json = new JSONObject();
		json.put("phoneNo", phoneNo);
		json.put("smsToken", smsToken);
		json.put("checkCode", checkCode);
		json.put("appKey", "1001");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/sms/verifyCheckCode",
				json.toString());
		System.out.println(re);
	}
}
