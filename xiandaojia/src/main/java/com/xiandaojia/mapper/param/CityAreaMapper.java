package com.xiandaojia.mapper.param;

import java.util.List;
import java.util.Map;

import com.xiandaojia.common.domain.CityArea;

public interface CityAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CityArea record);

    int insertSelective(CityArea record);

    CityArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CityArea record);

    int updateByPrimaryKey(CityArea record);
    
    List<CityArea> queryListByPlevel(Map<String,String> paramMap); 
    
}