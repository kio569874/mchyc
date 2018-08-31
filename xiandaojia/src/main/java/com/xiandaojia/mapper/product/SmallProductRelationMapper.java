package com.xiandaojia.mapper.product;

import com.xiandaojia.common.domain.SmallProductRelation;

import java.util.List;

public interface SmallProductRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmallProductRelation record);

    int insertSelective(SmallProductRelation record);

    SmallProductRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmallProductRelation record);

    int updateByPrimaryKey(SmallProductRelation record);
    
    int deleteByProductId(Long productId);

    List<SmallProductRelation> selectByProductId(Long id);
}