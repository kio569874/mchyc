package com.xiandaojia.service.product;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductBigTypeInfo;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IProductBigTypeInfoService extends IBaseService<ProductBigTypeInfo> {

	PaginationDto<ProductBigTypeInfo> queryProductListByPage(int page, int pageSize, Integer totalCount)
			throws SysException;
}