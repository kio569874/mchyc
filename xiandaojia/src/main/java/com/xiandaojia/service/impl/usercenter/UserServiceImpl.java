package com.xiandaojia.service.impl.usercenter;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.User;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.usercenter.UserMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.usercenter.IUserService;

@Service("userService")
public class UserServiceImpl extends AbstractBaseService implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void insert(User t) {
		t.setCreateTime(new Date());
		userMapper.insert(t);

	}

	@Override
	public void update(User t) {
		userMapper.updateByPrimaryKeySelective(t);

	}

	@Override
	public void delete(Long id) {
		userMapper.deleteByPrimaryKey(id);

	}

	@Override
	public PaginationDto<User> queryListByPage(int page, int pageSize, Integer totalCount) throws SysException {
		PaginationUtil.checkPaginationArgs(page, pageSize);
		PaginationDto<User> paginationDto = new PaginationDto<User>(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			totalCount = userMapper.getTotalCount();
		}
		paginationDto.setTotalCount(totalCount);
		List<User> list = userMapper.queryListByPage(offset, pageSize);
		paginationDto.setData(list);
		return paginationDto;
	}

	@Override
	public User checkUser(String userAccount, String userPassword) {
		return userMapper.checkUser(userAccount, userPassword);
	}
	@Override
	public User checkUserPhone(String userPhone) {
		return userMapper.checkUserPhone(userPhone);
	}

	@Override
	public int updatePassword(String phoneNo, String userPassword) {
		return userMapper.updatePassword(phoneNo, userPassword);
	}

	@Override
	public User selectByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
