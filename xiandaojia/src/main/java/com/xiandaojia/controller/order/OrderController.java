package com.xiandaojia.controller.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.domain.Order;
import com.xiandaojia.common.domain.OrderProduct;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.dto.OrderDto;
import com.xiandaojia.common.utils.JsonBeanUtil;
import com.xiandaojia.controller.BaseController;
import com.xiandaojia.service.order.IOrderService;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController {

	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public String insert(@RequestBody String content) {
		try {
			OrderDto orderDto = JsonBeanUtil.stringToBean(OrderDto.class, content);
			orderService.insert(orderDto);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody String content) {
		try {
			Order order = JsonBeanUtil.stringToBean(Order.class, content);
			orderService.delete(order.getOrderId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestBody String content) {
		try {
			OrderDto orderDto = JsonBeanUtil.stringToBean(OrderDto.class, content);
			orderService.update(orderDto);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public String query(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			Map<String, Object> paramMap = jsonObj;
			PaginationDto<Order> paginationDto = orderService.queryListByPage(page, pageSize, null, paramMap);
			return getSuccessResultMsg(JSONObject.toJSONString(paginationDto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	/**
	 * 根据产品id查询介绍等信息
	 * 
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/orderProduct/queryListByOrderId", method = RequestMethod.POST)
	@ResponseBody
	public String queryListByOrderId(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			Long orderId = jsonObj.getLong("orderId");
			List<OrderProduct> orderProductList = orderService.queryListByOrderId(orderId);
			if (orderProductList != null && orderProductList.size() > 0) {
				JSONObject list = new JSONObject();
				JSONArray jsonArr = (JSONArray) JSONArray.toJSON(orderProductList);
				list.put("data", jsonArr);
				return getSuccessResultMsg(list.toJSONString());
			} else {
				return getSuccessResultMsg(new JSONObject().toJSONString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}
}
