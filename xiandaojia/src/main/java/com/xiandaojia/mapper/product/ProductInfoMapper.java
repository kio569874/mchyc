package com.xiandaojia.mapper.product;

import java.util.List;
import java.util.Map;

import com.xiandaojia.common.domain.ProductInfo;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
    
	int getTotalCount(Map<String,Object> paramMap);

	List<ProductInfo> queryListByPage(Map<String,Object> paramMap);

    List<ProductInfo> selectListBigType(Map<String,Object> paramMap);

    int getTotalCountByBigType(Map<String,Object> paramMap);

}