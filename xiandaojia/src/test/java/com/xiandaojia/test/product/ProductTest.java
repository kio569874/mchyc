package com.xiandaojia.test.product;

import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.test.SendHttp;

public class ProductTest {

	public static void main(String[] args) {
		// productBigTypeInfoInsert();
		productSmallTypeInfoInsert();
		// productInfoInsert();

	}

	private static void productBigTypeInfoInsert() {
		JSONObject json = new JSONObject();
		json.put("bigtypeName", "果蔬到家");
		json.put("bigtypeSeqno", 0);
		json.put("status", "0");
		json.put("operator", "杨超");
		System.out.println(json);
		String re = SendHttp.getInstance()
				.sendPost("http://127.0.0.1:8999/xiandaojia/product/productBigTypeInfo/insert", json.toString());
		System.out.println(re);
	}

	private static void productSmallTypeInfoInsert() {
		JSONObject json = new JSONObject();
		json.put("smalltypeName", "热销");
		json.put("bigtypeId", 1);
		json.put("smallSeqno", 0);
		json.put("status", "0");
		json.put("operator", "yangchao");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/product/productSmallTypeInfo/insert",
				json.toString());
		System.out.println(re);
	}

	private static void productInfoInsert() {
		JSONObject json = new JSONObject();
		JSONObject productInfoJson = new JSONObject();
		productInfoJson.put("productName", "亚木沟生态草莓500g");
		productInfoJson.put("smalltypeId", 1);
		productInfoJson.put("productPrice", 15.50);
		productInfoJson.put("productUrl", "/product/" + UUID.randomUUID().toString() + ".jpg");
		productInfoJson.put("productSeqno", 0);
		productInfoJson.put("priceUnit", "15.50/斤");// 价格单位，直接根据价格带出来，默认XX/斤，可修改，如18.50/份
		productInfoJson.put("productNum", 500);
		productInfoJson.put("isDiscount", "0");// 0-是，1-否
		productInfoJson.put("productDiscount", 80);// 0-是，1-否
		productInfoJson.put("status", "0");
		productInfoJson.put("productDesc", "备注");
		JSONArray jsonArr = new JSONArray();
		JSONObject productInformationJson1 = new JSONObject();
		productInformationJson1.put("informationName", "产品介绍");
		productInformationJson1.put("informationContent", "来自亚木沟草莓种植基地，无公害生态种植，深山矿泉水源浇灌...");
		productInformationJson1.put("informationSeqno", 0);

		JSONObject productInformationJson2 = new JSONObject();
		productInformationJson2.put("informationName", "营养成分");
		productInformationJson2.put("informationContent", "来自亚木沟草莓种植基地，无公害生态种植，深山矿泉水源浇灌...");
		productInformationJson2.put("informationSeqno", 1);

		JSONObject productInformationJson3 = new JSONObject();
		productInformationJson3.put("informationName", "适宜人群");
		productInformationJson3.put("informationContent", "来自亚木沟草莓种植基地，无公害生态种植，深山矿泉水源浇灌...");
		productInformationJson3.put("informationSeqno", 2);

		JSONObject productInformationJson4 = new JSONObject();
		productInformationJson4.put("informationName", "产品知识");
		productInformationJson4.put("informationContent", "来自亚木沟草莓种植基地，无公害生态种植，深山矿泉水源浇灌...");
		productInformationJson4.put("informationSeqno", 3);
		jsonArr.add(productInformationJson1);
		jsonArr.add(productInformationJson2);
		jsonArr.add(productInformationJson3);
		jsonArr.add(productInformationJson4);
		json.put("productInfo", productInfoJson);
		json.put("productInformationList", jsonArr);
		System.out.println(json);
		// ProductDto productDto = JsonBeanUtil.jsonToBean(ProductDto.class, json);
		// System.out.println(productDto.getProductInfo().getProductName());
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/product/productInfo/insert",
				json.toString());
		System.out.println(re);
	}

}
