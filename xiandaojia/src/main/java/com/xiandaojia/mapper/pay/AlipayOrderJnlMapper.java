package com.xiandaojia.mapper.pay;

import com.xiandaojia.common.domain.AlipayOrderJnl;

public interface AlipayOrderJnlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlipayOrderJnl record);

    int insertSelective(AlipayOrderJnl record);

    AlipayOrderJnl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlipayOrderJnl record);

    int updateByPrimaryKey(AlipayOrderJnl record);
}