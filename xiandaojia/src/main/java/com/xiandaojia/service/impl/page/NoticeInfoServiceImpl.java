package com.xiandaojia.service.impl.page;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiandaojia.common.domain.NoticeInfo;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.page.NoticeInfoMapper;
import com.xiandaojia.service.page.INoticeInfoService;

@Service("noticeInfoService")
public class NoticeInfoServiceImpl implements INoticeInfoService {

	@Autowired
	NoticeInfoMapper noticeInfoMapper;

	@Override
	public void insert(NoticeInfo t) {
		t.setCreateTime(new Date());
		noticeInfoMapper.insert(t);

	}

	@Override
	public void update(NoticeInfo t) {
		t.setUpdateTime(new Date());
		noticeInfoMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public void delete(Long id) {
		noticeInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PaginationDto<NoticeInfo> querySystemUserListByPage(int page, int pageSize, Integer totalCount)
			throws SysException {
		PaginationUtil.checkPaginationArgs(page, pageSize);
		PaginationDto<NoticeInfo> paginationDto = new PaginationDto<NoticeInfo>(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			totalCount = noticeInfoMapper.getTotalCount();
		}
		paginationDto.setTotalCount(totalCount);
		List<NoticeInfo> list = noticeInfoMapper.queryListByPage(offset, pageSize);
		paginationDto.setData(list);
		return paginationDto;
	}
}
