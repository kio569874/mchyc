package com.xiandaojia.service.product;

import java.util.Map;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.exception.SysException;

public interface IProductService {

	String query(Map<String, Object> paramMap) throws SysException;

	String queryList(Map<String, Object> paramMap) throws SysException;


	String queryInfo(Map<String, Object> paramMap) throws SysException;
	
	PaginationDto<ProductInfo> queryProductListByPage(int page, int pageSize, Integer totalCount)
			throws SysException;
	
	
}
