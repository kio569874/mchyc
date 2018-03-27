package com.xiandaojia.mapper.sms;

import java.util.List;

import com.xiandaojia.common.domain.SmsCheckCodeJnl;

public interface SmsCheckCodeJnlMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SmsCheckCodeJnl record);

	int insertSelective(SmsCheckCodeJnl record);

	SmsCheckCodeJnl selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SmsCheckCodeJnl record);

	int updateByPrimaryKey(SmsCheckCodeJnl record);

//	int getTotalCount(String appKey, String phoneNo);
//
//	List<SmsCheckCodeJnl> queryListByPage(int offset, int pageSize, String appKey, String phoneNo);

	List<SmsCheckCodeJnl> querySmsCheckCodeList(String appKey, String phoneNo, String smsToken);
}