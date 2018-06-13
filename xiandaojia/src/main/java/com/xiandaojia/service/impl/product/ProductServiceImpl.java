package com.xiandaojia.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductBigTypeInfo;
import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.domain.ProductSmallTypeInfo;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.product.ProductBigTypeInfoMapper;
import com.xiandaojia.mapper.product.ProductInfoMapper;
import com.xiandaojia.mapper.product.ProductSmallTypeInfoMapper;
import com.xiandaojia.service.product.IProductService;

/**
 * app端产品信息查询
 * 
 * @author mchyc
 *
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {

	@Resource
	ProductBigTypeInfoMapper productBigTypeInfoMapper;

	@Autowired
	ProductSmallTypeInfoMapper productSmallTypeInfoMapper;

	@Autowired
	ProductInfoMapper productInfoMapper;

	@Override
	public String query(Map<String, Object> paramMap) throws SysException {
		// int page, int pageSize, Integer totalCount,
		// Map<String, Object> paramMap
		JSONObject resJson = new JSONObject();
		int page = paramMap.get("page") == null ? 1 : (int) paramMap.get("page");
		int pageSize = paramMap.get("pageSize") == null ? 1 : (int) paramMap.get("pageSize");
		Integer totalCount = paramMap.get("totalCount") == null ? 1 : (int) paramMap.get("totalCount");
		// 1.大类信息
		ProductBigTypeInfo productBigTypeInfo = new ProductBigTypeInfo();
		List<ProductBigTypeInfo> productBigTypeInfoList = productBigTypeInfoMapper.query(productBigTypeInfo);
		JSONArray bigTypeArr = new JSONArray();
		for (ProductBigTypeInfo temp : productBigTypeInfoList) {
			JSONObject bigJson = new JSONObject();
			bigJson.put("bigtypeId", temp.getBigtypeId());
			bigJson.put("bigtypeName", temp.getBigtypeName());
			bigJson.put("bigtypeSeqno", temp.getBigtypeSeqno());
			bigTypeArr.add(bigJson);
		}
		resJson.put("productBigTypeInfoList", bigTypeArr);
		// 2.查询小类信息
		ProductSmallTypeInfo productSmallTypeInfo = new ProductSmallTypeInfo();
		List<ProductSmallTypeInfo> productSmallTypeInfoList = productSmallTypeInfoMapper.query(productSmallTypeInfo);
		JSONArray smallTypeArr = new JSONArray();
		for (ProductSmallTypeInfo temp : productSmallTypeInfoList) {
			JSONObject smallJson = new JSONObject();
			smallJson.put("smalltypeId", temp.getSmalltypeId());
			smallJson.put("smalltypeName", temp.getSmalltypeName());
			smallJson.put("smallSeqno", temp.getSmallSeqno());
			smallTypeArr.add(smallJson);
		}
		resJson.put("productSmallTypeInfoList", smallTypeArr);

		// 根据小类id smalltypeId查询下面的产品,默认查询热销小类
		if (paramMap.get("smalltypeId") == null || "".equals(paramMap.get("smalltypeId"))) {
			paramMap.put("smalltypeId", productSmallTypeInfoList.get(0).getSmalltypeId());
		}
		PaginationUtil.checkPaginationArgs(page, pageSize);
		PaginationDto<ProductInfo> paginationDto = new PaginationDto<ProductInfo>(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			totalCount = productInfoMapper.getTotalCount(paramMap);
		}
		paginationDto.setTotalCount(totalCount);
		paramMap.put("offset", offset);
		paramMap.put("pageSize", pageSize);
		List<ProductInfo> list = productInfoMapper.queryListByPage(paramMap);
		paginationDto.setData(list);
		JSONObject productInfoJson = (JSONObject) JSONObject.toJSON(paginationDto);
		resJson.put("productInfoList", productInfoJson);
		return resJson.toJSONString();
	}

}
