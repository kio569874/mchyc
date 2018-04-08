package com.xiandaojia.service.impl.param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.domain.CityArea;
import com.xiandaojia.common.utils.MenuTreeUtil;
import com.xiandaojia.mapper.param.CityAreaMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.param.ICityAreaService;

@Service("cityAreaService")
public class CityAreaServiceImpl extends AbstractBaseService implements ICityAreaService {

	@Autowired
	private CityAreaMapper cityAreaMapper;

	@Override
	public void insert(CityArea t) {
		cityAreaMapper.insert(t);
	}

	@Override
	public void update(CityArea t) {
		cityAreaMapper.updateByPrimaryKeySelective(t);

	}

	@Override
	public void delete(Long id) {
		cityAreaMapper.deleteByPrimaryKey(id);

	}

	@Override
	public List<CityArea> queryListByPlevel(String plevel, String parentid) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!StringUtils.isEmpty(plevel)) {
			paramMap.put("plevel", plevel);
		}
		if (!StringUtils.isEmpty(parentid)) {
			paramMap.put("parentid", parentid);
		}
		return cityAreaMapper.queryListByPlevel(paramMap);
	}

	@Override
	public String queryListTree() {
		Map<String, String> paramMap = new HashMap<String, String>();
		List<CityArea> cityAreaList = cityAreaMapper.queryListByPlevel(paramMap);
		JSONArray jsonArray = new JSONArray();
		for (CityArea cityArea : cityAreaList) {
			// 生成菜单树
			JSONObject tempJson = new JSONObject();
			tempJson.put("id", cityArea.getId());
			tempJson.put("pid", cityArea.getPid());
			tempJson.put("parentid", cityArea.getParentid());
			tempJson.put("pcode", cityArea.getPcode());
			tempJson.put("pname", cityArea.getPname());
			tempJson.put("pshortname", cityArea.getPshortname());
			tempJson.put("plevel", cityArea.getPlevel());
			jsonArray.add(tempJson);
		}
		JSONArray jsonArr = MenuTreeUtil.treeMenuList(jsonArray, "0");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cityAreaList", jsonArr);
		return jsonObject.toJSONString();
	}
}
