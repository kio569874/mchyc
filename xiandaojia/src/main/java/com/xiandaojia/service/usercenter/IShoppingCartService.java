package com.xiandaojia.service.usercenter;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ShoppingCart;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IShoppingCartService extends IBaseService<ShoppingCart>{
	
	PaginationDto<ShoppingCart> queryListByPage(int page, int pageSize, Integer totalCount,Long userId) throws SysException;

}
