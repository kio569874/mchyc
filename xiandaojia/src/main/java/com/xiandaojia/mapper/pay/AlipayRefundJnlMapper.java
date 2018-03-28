package com.xiandaojia.mapper.pay;

import com.xiandaojia.common.domain.AlipayRefundJnl;

public interface AlipayRefundJnlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlipayRefundJnl record);

    int insertSelective(AlipayRefundJnl record);

    AlipayRefundJnl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlipayRefundJnl record);

    int updateByPrimaryKey(AlipayRefundJnl record);
}