package com.xiandaojia.common.dto;

import java.util.List;

import com.xiandaojia.common.domain.Order;
import com.xiandaojia.common.domain.OrderProduct;

public class OrderDto {

	private Order order;
	private List<OrderProduct> orderProductList;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

}
