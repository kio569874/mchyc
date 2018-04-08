package com.xiandaojia.service.param;

import java.util.List;

import com.xiandaojia.common.domain.CityArea;
import com.xiandaojia.service.IBaseService;

public interface ICityAreaService extends IBaseService<CityArea>{
	
	/**
	 * 根据等级查询，比如省、省下面的市、市下面的区等
	 * @param plevel
	 * @return
	 */
	List<CityArea> queryListByPlevel(String plevel,String parentid); 
	
	/**
	 * 查询树
	 * @return
	 */
	String queryListTree();

}
