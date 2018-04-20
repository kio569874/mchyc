package com.xiandaojia.service.product;

import java.util.Map;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductSmallTypeInfo;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IProductSmallTypeInfoService extends IBaseService<ProductSmallTypeInfo> {
	
	PaginationDto<ProductSmallTypeInfo> queryProductListByPage(int page, int pageSize, Integer totalCount,
			Map<String, Object> map) throws SysException;
}
