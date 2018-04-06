package com.xiandaojia.test.product;

import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.enums.StatusEnum;
import com.xiandaojia.test.SendHttp;

public class ProductTest {

	public static void main(String[] args) {
		// 大类插入
		// productBigTypeInfoInsert();
		// 属性插入
		// productSmallTypeInfoInsert();
		// 介绍信息插入
		// productInformationInsert();
		// 产品信息插入
		// productInfoInsert();
		// 产品信息分页查询
		// queryProductInfo();
		// 根据产品id查询介绍信息
		productInformationQureyByProductId();

	}

	private static void productBigTypeInfoInsert() {
		JSONObject json = new JSONObject();
		json.put("bigtypeName", "美食推荐");
		json.put("bigtypeSeqno", 2);
		json.put("status", StatusEnum.START.getStatus());
		json.put("operator", "yangchao");
		System.out.println(json);
		String re = SendHttp.getInstance()
				.sendPost("http://127.0.0.1:8999/xiandaojia/product/productBigTypeInfo/insert", json.toString());
		System.out.println(re);
	}

	private static void productSmallTypeInfoInsert() {
		JSONObject json = new JSONObject();
		json.put("smalltypeName", "植被");
		json.put("smallSeqno", 9);
		json.put("status", "0");
		json.put("operator", "yangchao");
		System.out.println(json);
		String re = SendHttp.getInstance()
				.sendPost("http://127.0.0.1:8999/xiandaojia/product/productSmallTypeInfo/insert", json.toString());
		System.out.println(re);
	}

	private static void productInformationInsert() {
		JSONObject json = new JSONObject();
		json.put("informationName", "产品知识");
		json.put("informationContent", "产品很好很nice");
		json.put("informationDesc", "备注");
		System.out.println(json);
		String re = SendHttp.getInstance()
				.sendPost("http://127.0.0.1:8999/xiandaojia/product/productInformation/insert", json.toString());
		System.out.println(re);
	}

	private static void productInfoInsert() {
		JSONObject json = new JSONObject();
		JSONObject productInfoJson = new JSONObject();
		productInfoJson.put("productName", "亚木沟生态草莓500g");
		productInfoJson.put("bigtypeId", 1);
		productInfoJson.put("productPrice", 15.50);
		productInfoJson.put("productUrl", "/product/" + UUID.randomUUID().toString() + ".jpg");
		productInfoJson.put("productSeqno", 0);
		productInfoJson.put("priceUnit", "15.50/斤");// 价格单位，直接根据价格带出来，默认XX/斤，可修改，如18.50/份
		productInfoJson.put("productNum", 500);
		productInfoJson.put("isDiscount", "0");// 0-是，1-否
		productInfoJson.put("productDiscount", 80);// 0-是，1-否
		productInfoJson.put("status", "0");
		productInfoJson.put("productDesc", "备注");
		JSONArray productInformationRelationArr = new JSONArray();
		JSONObject productInformationRelationJson1 = new JSONObject();
		productInformationRelationJson1.put("informationId", 1);
		productInformationRelationJson1.put("informationSeqno", 0);
		JSONObject productInformationRelationJson2 = new JSONObject();
		productInformationRelationJson2.put("informationId", 2);
		productInformationRelationJson2.put("informationSeqno", 1);
		JSONObject productInformationRelationJson3 = new JSONObject();
		productInformationRelationJson3.put("informationId", 3);
		productInformationRelationJson3.put("informationSeqno", 2);
		JSONObject productInformationRelationJson4 = new JSONObject();
		productInformationRelationJson4.put("informationId", 4);
		productInformationRelationJson4.put("informationSeqno", 3);

		productInformationRelationArr.add(productInformationRelationJson1);
		productInformationRelationArr.add(productInformationRelationJson2);
		productInformationRelationArr.add(productInformationRelationJson3);
		productInformationRelationArr.add(productInformationRelationJson4);

		JSONArray smallProductRelationArr = new JSONArray();
		JSONObject smallProductRelationJson1 = new JSONObject();
		smallProductRelationJson1.put("smalltypeId", 1);
		JSONObject smallProductRelationJson2 = new JSONObject();
		smallProductRelationJson2.put("smalltypeId", 2);
		smallProductRelationArr.add(smallProductRelationJson1);
		smallProductRelationArr.add(smallProductRelationJson2);

		json.put("productInfo", productInfoJson);
		json.put("productInformationRelationList", productInformationRelationArr);
		json.put("smallProductRelationList", smallProductRelationArr);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/product/productInfo/insert",
				json.toString());
		System.out.println(re);
	}

	private static void queryProductInfo() {
		JSONObject json = new JSONObject();
		json.put("page", 1);
		json.put("pageSize", 10);
		// json.put("smalltypeId", 1);
		// json.put("bigtypeId", 1);
		// json.put("productName", "草莓");
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost("http://127.0.0.1:8999/xiandaojia/product/productInfo/query",
				json.toString());
		System.out.println(re);
	}

	private static void productInformationQureyByProductId() {
		JSONObject json = new JSONObject();
		json.put("productId", 2);
		System.out.println(json);
		String re = SendHttp.getInstance().sendPost(
				"http://127.0.0.1:8999/xiandaojia/product/productInformation/queryByProductId", json.toString());
		System.out.println(re);
	}

}
