package com.xiandaojia.mapper.product;

import com.xiandaojia.common.domain.ProductInformationRelation;

import java.util.List;

public interface ProductInformationRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductInformationRelation record);

    int insertSelective(ProductInformationRelation record);

    ProductInformationRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInformationRelation record);

    int updateByPrimaryKey(ProductInformationRelation record);
    
    int deleteByProductId(Long productId);

    List<ProductInformationRelation> selectByProductId(Long id);
}