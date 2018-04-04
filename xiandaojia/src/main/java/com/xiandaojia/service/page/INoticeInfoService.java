package com.xiandaojia.service.page;

import com.xiandaojia.common.domain.NoticeInfo;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface INoticeInfoService extends IBaseService<NoticeInfo>{
	
	PaginationDto<NoticeInfo> querySystemUserListByPage(int page, int pageSize, Integer totalCount) throws SysException;
}
