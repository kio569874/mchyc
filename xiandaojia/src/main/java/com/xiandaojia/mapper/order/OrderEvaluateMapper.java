package com.xiandaojia.mapper.order;

import com.xiandaojia.common.domain.OrderEvaluate;

public interface OrderEvaluateMapper {
    int deleteByPrimaryKey(Long evaluateId);

    int insert(OrderEvaluate record);

    int insertSelective(OrderEvaluate record);

    OrderEvaluate selectByPrimaryKey(Long evaluateId);

    int updateByPrimaryKeySelective(OrderEvaluate record);

    int updateByPrimaryKey(OrderEvaluate record);
}