package com.xiandaojia.service.impl.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiandaojia.common.domain.Order;
import com.xiandaojia.common.domain.OrderProduct;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.dto.OrderDto;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.order.OrderEvaluateMapper;
import com.xiandaojia.mapper.order.OrderMapper;
import com.xiandaojia.mapper.order.OrderProductMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.order.IOrderService;

/**
 * 订单服务实现
 * 
 * @author mchyc
 *
 */
@Service("orderService")
public class OrderServiceImpl extends AbstractBaseService implements IOrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderProductMapper orderProductMapper;

	@Autowired
	private OrderEvaluateMapper orderEvaluateMapper;

	@Override
	public void insert(Object t) {
		OrderDto orderDto = (OrderDto) t;
		Order order = orderDto.getOrder();
		order.setVersion(0L);
		order.setCreateTime(new Date());
		orderMapper.insert(order);

		List<OrderProduct> orderProductList = orderDto.getOrderProductList();
		if (orderProductList != null && orderProductList.size() > 0) {
			for (OrderProduct orderProduct : orderProductList) {
				if (StringUtils.isEmpty(orderProduct.getOrderId())) {
					orderProduct.setOrderId(order.getOrderId());
				}
				orderProductMapper.insert(orderProduct);
			}
		}
	}

	@Override
	public void update(Object t) {
		OrderDto orderDto = (OrderDto) t;
		Order order = orderDto.getOrder();
		order.setUpdateTime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);

	}

	@Override
	public void delete(Long id) {
		orderMapper.deleteByPrimaryKey(id);
		orderProductMapper.deleteByOrderId(id);

	}

	@Override
	public PaginationDto<Order> queryListByPage(int page, int pageSize, Integer totalCount,
			Map<String, Object> paramMap) throws SysException {
		PaginationUtil.checkPaginationArgs(page, pageSize);
		PaginationDto<Order> paginationDto = new PaginationDto<Order>(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			totalCount = orderMapper.getTotalCount(paramMap);
		}
		paginationDto.setTotalCount(totalCount);
		paramMap.put("offset", offset);
		paramMap.put("pageSize", pageSize);
		List<Order> list = orderMapper.queryListByPage(paramMap);
		paginationDto.setData(list);
		return paginationDto;
	}

	@Override
	public List<OrderProduct> queryListByOrderId(Long orderId) {
		return orderProductMapper.queryListByOrderId(orderId);
	}

}
