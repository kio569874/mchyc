package com.xiandaojia.common.utils;

import com.xiandaojia.common.exception.SysException;

/**
 * 分页工具类
 */
public abstract class PaginationUtil {

	/**
	 * 检测分页参数是否正确
	 * 
	 * @param page
	 *            当前页码
	 * @param pageSize
	 *            每页记录数
	 * @throws TaxationException
	 */
	public static void checkPaginationArgs(int page, int pageSize) throws SysException {
		if (page <= 0) {
			throw new SysException("指定的获取的页数不正确认。page:" + page);
		} else if (pageSize <= 0) {
			throw new SysException("指定的每页显示记录数不正确。pageSize:" + pageSize);
		}
	}
}
