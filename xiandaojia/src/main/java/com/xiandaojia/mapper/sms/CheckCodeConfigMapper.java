package com.xiandaojia.mapper.sms;

import java.util.List;

import com.xiandaojia.common.domain.CheckCodeConfig;

public interface CheckCodeConfigMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CheckCodeConfig record);

	int insertSelective(CheckCodeConfig record);

	CheckCodeConfig selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CheckCodeConfig record);

	int updateByPrimaryKey(CheckCodeConfig record);

	List<CheckCodeConfig> queryCheckCodeConfigList(String appKey);

}