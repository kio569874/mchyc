package com.xiandaojia.mapper.pay;

import com.xiandaojia.common.domain.AlipayOrderJnlExt;

public interface AlipayOrderJnlExtMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlipayOrderJnlExt record);

    int insertSelective(AlipayOrderJnlExt record);

    AlipayOrderJnlExt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlipayOrderJnlExt record);

    int updateByPrimaryKeyWithBLOBs(AlipayOrderJnlExt record);

    int updateByPrimaryKey(AlipayOrderJnlExt record);
}