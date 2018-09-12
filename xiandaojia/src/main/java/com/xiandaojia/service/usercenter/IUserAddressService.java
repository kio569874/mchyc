package com.xiandaojia.service.usercenter;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.UserAddress;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IUserAddressService extends IBaseService<UserAddress> {
	PaginationDto<UserAddress> queryListByPage(int page, int pageSize, Integer totalCount, Long userId)
			throws SysException;

	String queryList(Long userId);
}
