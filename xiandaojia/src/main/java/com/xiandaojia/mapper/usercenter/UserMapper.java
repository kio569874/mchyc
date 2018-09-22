package com.xiandaojia.mapper.usercenter;

import java.util.List;

import com.xiandaojia.common.domain.User;

public interface UserMapper {
	int deleteByPrimaryKey(Long userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Long userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	int getTotalCount();

	List<User> queryListByPage(int offset, int pageSize);

	User checkUser(String userAccount, String userPassword);

	int updatePassword(String phoneNo, String userPassword);

	User checkUserPhone(String userPhone);
}