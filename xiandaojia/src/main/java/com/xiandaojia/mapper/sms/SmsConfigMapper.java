package com.xiandaojia.mapper.sms;

import java.util.List;

import com.xiandaojia.common.domain.SmsConfig;

public interface SmsConfigMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SmsConfig record);

	int insertSelective(SmsConfig record);

	SmsConfig selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SmsConfig record);

	int updateByPrimaryKey(SmsConfig record);

	List<SmsConfig> querySmsConfigList();
}