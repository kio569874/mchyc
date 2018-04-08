package com.xiandaojia.controller.param;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.domain.CityArea;
import com.xiandaojia.common.utils.JsonBeanUtil;
import com.xiandaojia.controller.BaseController;
import com.xiandaojia.service.param.ICityAreaService;

@RestController
@RequestMapping("param")
public class CityAreaController extends BaseController {

	@Autowired
	private ICityAreaService cityAreaService;

	@RequestMapping(value = "/cityArea/insert", method = RequestMethod.POST)
	@ResponseBody
	public String insert(@RequestBody String content) {
		try {
			CityArea t = JsonBeanUtil.stringToBean(CityArea.class, content);
			cityAreaService.insert(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/cityArea/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody String content) {
		try {
			CityArea t = JsonBeanUtil.stringToBean(CityArea.class, content);
			cityAreaService.delete(t.getId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/cityArea/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestBody String content) {
		try {
			CityArea t = JsonBeanUtil.stringToBean(CityArea.class, content);
			cityAreaService.update(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/cityArea/queryListTree", method = RequestMethod.POST)
	@ResponseBody
	public String queryListTree(@RequestBody String content) {
		try {
			String result = cityAreaService.queryListTree();
			return getSuccessResultMsg(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/cityArea/query", method = RequestMethod.POST)
	@ResponseBody
	public String query(@RequestBody String content) {
		try {
			JSONObject jsonObject = JSONObject.parseObject(content);
			String plevel = jsonObject.get("plevel") == null ? null : jsonObject.get("plevel").toString();
			String parentid = jsonObject.get("parentid") == null ? null : jsonObject.get("parentid").toString();
			List<CityArea> cityAreaList = cityAreaService.queryListByPlevel(plevel, parentid);
			JSONObject list = new JSONObject();
			JSONArray jsonArr = (JSONArray) JSONArray.toJSON(cityAreaList);
			list.put("listData", jsonArr);
			return getSuccessResultMsg(list.toJSONString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}
}
