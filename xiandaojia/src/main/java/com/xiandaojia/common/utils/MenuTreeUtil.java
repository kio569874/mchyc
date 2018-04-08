package com.xiandaojia.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MenuTreeUtil {

	@SuppressWarnings("unchecked")
	public static JSONArray treeMenuList(JSONArray menuList, String parentId) {
		JSONArray childMenu = new JSONArray();
		for (Object object : menuList) {
			JSONObject jsonMenu = (JSONObject) object;
			String pid = jsonMenu.getString("pid");
			String parentid = jsonMenu.getString("parentid");
			if (parentId.equals(parentid)) {
				JSONArray childArr = treeMenuList(menuList, pid);
				jsonMenu.put("childNode", childArr);
				childMenu.add(jsonMenu);
			}
		}
		return childMenu;
	}
}
