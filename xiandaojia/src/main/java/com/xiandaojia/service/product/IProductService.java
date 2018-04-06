package com.xiandaojia.service.product;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IProductService<T> extends IBaseService<T> {
	
	/**
	 * 根据小类分页查询产品信息
	 * 
	 * @param page
	 * @param pageSize
	 * @param totalCount
	 * @param userId
	 * @return
	 */
	PaginationDto<T> queryProductListByPage(int page, int pageSize, Integer totalCount)
			throws SysException;
	
}
