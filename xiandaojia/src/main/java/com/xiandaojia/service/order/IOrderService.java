package com.xiandaojia.service.order;

import java.util.List;
import java.util.Map;

import com.xiandaojia.common.domain.Order;
import com.xiandaojia.common.domain.OrderProduct;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.service.IBaseService;

public interface IOrderService extends IBaseService {

	/**
	 * 查询订单信息
	 * 
	 * @param page
	 * @param pageSize
	 * @param totalCount
	 * @param paramMap
	 * @return
	 * @throws SysException
	 */
	PaginationDto<Order> queryListByPage(int page, int pageSize, Integer totalCount, Map<String, Object> paramMap)
			throws SysException;

	List<OrderProduct> queryListByOrderId(Long orderId);
}
