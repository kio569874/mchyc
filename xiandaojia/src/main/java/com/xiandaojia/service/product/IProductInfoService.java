package com.xiandaojia.service.product;

import java.util.Map;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.exception.SysException;

public interface IProductInfoService extends IProductService {

	PaginationDto<ProductInfo> queryProductListByPage(int page, int pageSize, Integer totalCount,
			Map<String, Object> map) throws SysException;

}
