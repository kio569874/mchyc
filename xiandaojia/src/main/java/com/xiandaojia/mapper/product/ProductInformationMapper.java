package com.xiandaojia.mapper.product;

import java.util.List;

import com.xiandaojia.common.domain.ProductInformation;

public interface ProductInformationMapper {
	int deleteByPrimaryKey(Long informationId);

	int insert(ProductInformation record);

	int insertSelective(ProductInformation record);

	ProductInformation selectByPrimaryKey(Long informationId);

	int updateByPrimaryKeySelective(ProductInformation record);

	int updateByPrimaryKey(ProductInformation record);

	List<ProductInformation> queryListByProductId(Long productId);

	int deleteByProductId(Long productId);
}