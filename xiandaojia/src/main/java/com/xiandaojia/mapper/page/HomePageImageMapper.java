package com.xiandaojia.mapper.page;

import java.util.List;

import com.xiandaojia.common.domain.HomePageImage;

public interface HomePageImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HomePageImage record);

    int insertSelective(HomePageImage record);

    HomePageImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HomePageImage record);

    int updateByPrimaryKey(HomePageImage record);
    
    int getTotalCount();

  	List<HomePageImage> queryListByPage(int offset, int pageSize);
}