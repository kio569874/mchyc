package com.xiandaojia.mapper.product;

import java.util.List;

import com.xiandaojia.common.domain.ProductInfo;

public interface ProductInfoMapper {
	int deleteByPrimaryKey(Long productId);

	int insert(ProductInfo record);

	int insertSelective(ProductInfo record);

	ProductInfo selectByPrimaryKey(Long productId);

	int updateByPrimaryKeySelective(ProductInfo record);

	int updateByPrimaryKey(ProductInfo record);

	int getTotalCount(Long smalltypeId);

	List<ProductInfo> queryListByPage(int offset, int pageSize, Long smalltypeId);
}