package com.xiandaojia.service.usercenter;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.SystemUser;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface ISystemUserService extends IBaseService<SystemUser> {

	PaginationDto<SystemUser> queryListByPage(int page, int pageSize, Integer totalCount) throws SysException;

	SystemUser checkSystemUser(String userCode, String userPassword);

	SystemUser selectUserById(Long id);

}
