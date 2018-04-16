package com.xiandaojia.mapper.usercenter;

import java.util.List;

import com.xiandaojia.common.domain.ShoppingCart;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
    
    int getTotalCount(Long userId);

	List<ShoppingCart> queryListByPage(int offset, int pageSize,Long userId);
}