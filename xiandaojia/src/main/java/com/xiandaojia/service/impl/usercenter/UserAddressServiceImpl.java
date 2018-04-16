package com.xiandaojia.service.impl.usercenter;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.UserAddress;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.usercenter.UserAddressMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.usercenter.IUserAddressService;

@Service("userAddressService")
public class UserAddressServiceImpl extends AbstractBaseService implements IUserAddressService {

	@Autowired
	private UserAddressMapper userAddressMapper;

	@Override
	public void insert(UserAddress t) {
		t.setCreateTime(new Date());
		userAddressMapper.insert(t);
	}

	@Override
	public void update(UserAddress t) {
		t.setUpdateTime(new Date());
		userAddressMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public void delete(Long id) {
		userAddressMapper.deleteByPrimaryKey(id);

	}

	@Override
	public PaginationDto<UserAddress> queryListByPage(int page, int pageSize, Integer totalCount, Long userId)
			throws SysException {
		PaginationDto<UserAddress> paginationDto = new PaginationDto<UserAddress>();
		PaginationUtil.checkPaginationArgs(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			paginationDto.setTotalCount(userAddressMapper.getTotalCount(userId));
		}
		List<UserAddress> list = userAddressMapper.queryListByPage(offset, pageSize, userId);
		paginationDto.setData(list);
		return paginationDto;
	}

}
