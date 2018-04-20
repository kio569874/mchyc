package com.xiandaojia.mapper.order;

import java.util.List;
import java.util.Map;

import com.xiandaojia.common.domain.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    int getTotalCount(Map<String,Object> paramMap);

	List<Order> queryListByPage(Map<String,Object> paramMap);
}