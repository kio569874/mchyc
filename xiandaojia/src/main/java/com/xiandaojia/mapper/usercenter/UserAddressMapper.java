package com.xiandaojia.mapper.usercenter;

import com.xiandaojia.common.domain.UserAddress;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
}