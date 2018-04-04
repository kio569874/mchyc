package com.xiandaojia.mapper.page;

import java.util.List;

import com.xiandaojia.common.domain.NoticeInfo;

public interface NoticeInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoticeInfo record);

    int insertSelective(NoticeInfo record);

    NoticeInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NoticeInfo record);

    int updateByPrimaryKey(NoticeInfo record);
    
    int getTotalCount();

  	List<NoticeInfo> queryListByPage(int offset, int pageSize);
}