package com.xiandaojia.service.impl.usercenter;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.mapper.product.ProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ShoppingCart;
import com.xiandaojia.common.domain.SystemUser;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.usercenter.ShoppingCartMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.usercenter.IShoppingCartService;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends AbstractBaseService implements IShoppingCartService {

	@Autowired
	private ShoppingCartMapper shoppingCartMapper;

	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Override
	public void insert(ShoppingCart t) {
		// TODO 校验userId和productId
		t.setCreateTime(new Date());
		shoppingCartMapper.insert(t);
	}

	@Override
	public void update(ShoppingCart t) {
		shoppingCartMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public void delete(Long id) {
		shoppingCartMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PaginationDto<ShoppingCart> queryListByPage(int page, int pageSize, Integer totalCount, Long userId)
			throws SysException {
		PaginationUtil.checkPaginationArgs(page, pageSize);
		PaginationDto<ShoppingCart> paginationDto = new PaginationDto<ShoppingCart>(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			totalCount = shoppingCartMapper.getTotalCount(userId);
		}
		paginationDto.setTotalCount(totalCount);
		List<ShoppingCart> list = shoppingCartMapper.queryListByPage(offset, pageSize, userId);
		paginationDto.setData(list);
		return paginationDto;
	}

	@Override
	public String queryListByUserId(Long userId) {
		JSONObject resJsonObject = new JSONObject();//返回的ＪＳＯＮ串
		List<ShoppingCart> list= shoppingCartMapper.queryListByUserId(userId);
		JSONArray jsonArray = new JSONArray();
		for(ShoppingCart cart:list){//遍历获取购物车信息,
			ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(Long.valueOf(cart.getProductId()));
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("productInfo",productInfo);
			jsonObject.put("shoppingCartInfo",cart);
			jsonArray.add(jsonObject);
		}
		resJsonObject.put("ShoppingCartList",jsonArray);
		return resJsonObject.toJSONString();
	}

	@Override
	public int upadteStateAll(ShoppingCart cart) {
		return shoppingCartMapper.updateByUserId(cart);
	}

}
