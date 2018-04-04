package com.xiandaojia.service.page;

import com.xiandaojia.common.domain.HomePageImage;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IHomePageImageService extends IBaseService<HomePageImage>{
	
	PaginationDto<HomePageImage> querySystemUserListByPage(int page, int pageSize, Integer totalCount) throws SysException;
}
