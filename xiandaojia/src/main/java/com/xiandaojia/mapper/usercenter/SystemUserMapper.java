package com.xiandaojia.mapper.usercenter;

import java.util.List;

import com.xiandaojia.common.domain.SystemUser;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);
    
    int getTotalCount();

	List<SystemUser> queryListByPage(int offset, int pageSize);
	
	SystemUser checkSystemUser(String userCode,String userPassword);
}