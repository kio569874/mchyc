package com.xiandaojia.service.product;

import java.util.Map;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IProductInfoService extends IBaseService {

	PaginationDto<ProductInfo> queryProductListByPage(int page, int pageSize, Integer totalCount,
			Map<String, Object> map) throws SysException;

}
