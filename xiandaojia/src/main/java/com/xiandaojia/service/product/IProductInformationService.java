package com.xiandaojia.service.product;

import java.util.List;
import java.util.Map;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductInformation;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IProductInformationService extends IBaseService<ProductInformation>{
	
	List<ProductInformation> queryListByProductId(Long productId);
	
	PaginationDto<ProductInformation> queryProductListByPage(int page, int pageSize, Integer totalCount,
			Map<String, Object> map) throws SysException;
	
	
}
