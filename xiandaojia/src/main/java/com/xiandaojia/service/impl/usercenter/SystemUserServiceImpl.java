package com.xiandaojia.service.impl.usercenter;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.SystemUser;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.usercenter.SystemUserMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.usercenter.ISystemUserService;

@Service("systemUserService")
public class SystemUserServiceImpl extends AbstractBaseService implements ISystemUserService {

	@Autowired
	private SystemUserMapper systemUserMapper;

	@Override
	public void insert(SystemUser t) {
		t.setCreateTime(new Date());
		systemUserMapper.insert(t);

	}

	@Override
	public void update(SystemUser t) {
		t.setUpdateTime(new Date());
		systemUserMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public void delete(Long id) {
		systemUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PaginationDto<SystemUser> queryListByPage(int page, int pageSize, Integer totalCount) throws SysException {
		PaginationUtil.checkPaginationArgs(page, pageSize);
		PaginationDto<SystemUser> paginationDto = new PaginationDto<SystemUser>(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			totalCount = systemUserMapper.getTotalCount();
		}
		paginationDto.setTotalCount(totalCount);
		List<SystemUser> list = systemUserMapper.queryListByPage(offset, pageSize);
		paginationDto.setData(list);
		return paginationDto;
	}

	@Override
	public SystemUser checkSystemUser(String userCode, String userPassword) {
		return systemUserMapper.checkSystemUser(userCode, userPassword);
	}

	@Override
	public SystemUser selectUserById(Long id) {
		return systemUserMapper.selectByPrimaryKey(id);
	}

}
