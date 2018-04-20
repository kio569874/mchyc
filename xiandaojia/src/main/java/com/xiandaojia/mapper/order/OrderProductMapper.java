package com.xiandaojia.mapper.order;

import java.util.List;

import com.xiandaojia.common.domain.OrderProduct;

public interface OrderProductMapper {
    int deleteByPrimaryKey(Long orderProductId);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(Long orderProductId);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);
    
    int deleteByOrderId(Long orderId);
    
    List<OrderProduct> queryListByOrderId(Long orderId);
}