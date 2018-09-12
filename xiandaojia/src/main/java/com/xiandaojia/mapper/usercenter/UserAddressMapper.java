package com.xiandaojia.mapper.usercenter;

import java.util.List;

import com.xiandaojia.common.domain.UserAddress;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
    
    int getTotalCount(Long userId);

	List<UserAddress> queryListByPage(int offset, int pageSize,Long userId);

    List<UserAddress> queryList(Long userId);
}