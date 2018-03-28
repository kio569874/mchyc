package com.xiandaojia.mapper.pay;

import com.xiandaojia.common.domain.AlipayRefundJnlExt;

public interface AlipayRefundJnlExtMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlipayRefundJnlExt record);

    int insertSelective(AlipayRefundJnlExt record);

    AlipayRefundJnlExt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlipayRefundJnlExt record);

    int updateByPrimaryKeyWithBLOBs(AlipayRefundJnlExt record);

    int updateByPrimaryKey(AlipayRefundJnlExt record);
}