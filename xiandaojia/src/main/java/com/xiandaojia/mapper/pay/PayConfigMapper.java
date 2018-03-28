package com.xiandaojia.mapper.pay;

import com.xiandaojia.common.domain.PayConfig;

public interface PayConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayConfig record);

    int insertSelective(PayConfig record);

    PayConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayConfig record);

    int updateByPrimaryKey(PayConfig record);
}