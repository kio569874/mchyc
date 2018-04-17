package com.xiandaojia.service.impl.page;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiandaojia.common.HomePageContants;
import com.xiandaojia.common.domain.HomePageImage;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.page.HomePageImageMapper;
import com.xiandaojia.service.page.IHomePageImageService;

@Service("homePageImageService")
public class HomePageImageServiceImpl implements IHomePageImageService {

	@Autowired
	HomePageImageMapper homePageImageMapper;

	@Override
	public void insert(HomePageImage t) {
		t.setCreateTime(new Date());
		homePageImageMapper.insert(t);
	}

	@Override
	public void update(HomePageImage t) {
		t.setUpdateTime(new Date());
		homePageImageMapper.updateByPrimaryKeySelective(t);

	}

	@Override
	public void delete(Long id) {
		homePageImageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PaginationDto<HomePageImage> querySystemUserListByPage(int page, int pageSize, Integer totalCount)
			throws SysException {
		PaginationUtil.checkPaginationArgs(page, pageSize);
		PaginationDto<HomePageImage> paginationDto = new PaginationDto<HomePageImage>(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			totalCount = homePageImageMapper.getTotalCount();
		}
		paginationDto.setTotalCount(totalCount);
		List<HomePageImage> list = homePageImageMapper.queryListByPage(offset, pageSize);
		paginationDto.setData(list);
		return paginationDto;
	}
}
