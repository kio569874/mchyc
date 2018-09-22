package com.xiandaojia.service.usercenter;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.User;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IUserService extends IBaseService<User> {
	/**
	 * 后台使用-分页查询客户相关信息
	 * 
	 * @param page
	 * @param pageSize
	 * @param totalCount
	 * @return
	 * @throws SysException
	 */
	PaginationDto<User> queryListByPage(int page, int pageSize, Integer totalCount) throws SysException;

	/**
	 * 前端登录使用，校验用户
	 * 
	 * @param userAccount
	 * @param userPassword
	 * @return
	 */
	User checkUser(String userAccount, String userPassword);

	int updatePassword(String phoneNo, String userPassword);
	
	User checkUserPhone(String userPhone);
	
	User selectByPrimaryKey(Long id);
}
