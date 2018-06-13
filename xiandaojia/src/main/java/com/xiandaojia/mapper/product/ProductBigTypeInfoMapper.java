package com.xiandaojia.mapper.product;

import java.util.List;

import com.xiandaojia.common.domain.ProductBigTypeInfo;

public interface ProductBigTypeInfoMapper {
    int deleteByPrimaryKey(Long bigtypeId);

    int insert(ProductBigTypeInfo record);

    int insertSelective(ProductBigTypeInfo record);

    ProductBigTypeInfo selectByPrimaryKey(Long bigtypeId);

    int updateByPrimaryKeySelective(ProductBigTypeInfo record);

    int updateByPrimaryKey(ProductBigTypeInfo record);
    
	int getTotalCount();

	List<ProductBigTypeInfo> queryListByPage(int offset, int pageSize);
	
	List<ProductBigTypeInfo> query(ProductBigTypeInfo productBigTypeInfo);
}