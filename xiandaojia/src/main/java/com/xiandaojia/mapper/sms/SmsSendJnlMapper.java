package com.xiandaojia.mapper.sms;

import com.xiandaojia.common.domain.SmsSendJnl;

public interface SmsSendJnlMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SmsSendJnl record);

	int insertSelective(SmsSendJnl record);

	SmsSendJnl selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SmsSendJnl record);

	int updateByPrimaryKey(SmsSendJnl record);

}