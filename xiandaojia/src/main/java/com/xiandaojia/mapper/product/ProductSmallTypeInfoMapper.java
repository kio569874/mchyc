package com.xiandaojia.mapper.product;

import java.util.List;

import com.xiandaojia.common.domain.ProductSmallTypeInfo;

public interface ProductSmallTypeInfoMapper {
    int deleteByPrimaryKey(Long smalltypeId);

    int insert(ProductSmallTypeInfo record);

    int insertSelective(ProductSmallTypeInfo record);

    ProductSmallTypeInfo selectByPrimaryKey(Long smalltypeId);

    int updateByPrimaryKeySelective(ProductSmallTypeInfo record);

    int updateByPrimaryKey(ProductSmallTypeInfo record);
    
	int getTotalCount();

	List<ProductSmallTypeInfo> queryListByPage(int offset, int pageSize);
}