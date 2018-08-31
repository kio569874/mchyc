package com.xiandaojia.service.product;

import java.util.Map;

import com.xiandaojia.common.exception.SysException;

public interface IProductService {

	String query(Map<String, Object> paramMap) throws SysException;

	String queryList(Map<String, Object> paramMap) throws SysException;

}
