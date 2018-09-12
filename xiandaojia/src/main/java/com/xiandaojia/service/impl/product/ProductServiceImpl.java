package com.xiandaojia.service.impl.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.xiandaojia.common.domain.*;
import com.xiandaojia.common.dto.ProductInfoVo;
import com.xiandaojia.mapper.product.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
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

	@Autowired
	SmallProductRelationMapper smallProductRelationMapper;

	@Autowired
	ProductInformationMapper productInformationMapper;

	@Autowired
	ProductInformationRelationMapper productInformationRelationMapper;

	@Override
	public String queryList(Map<String, Object> paramMap) throws SysException {
		// int page, int pageSize, Integer totalCount,
		// Map<String, Object> paramMap
		JSONObject resJson = new JSONObject();
		/*int page = paramMap.get("page") == null ? 1 : (int) paramMap.get("page");
		int pageSize = paramMap.get("pageSize") == null ? 1 : (int) paramMap.get("pageSize");*/
		//Integer totalCount = paramMap.get("totalCount") == null ? 1 : (int) paramMap.get("totalCount");
		Map<String, Object> paramMap2 = (Map<String, Object>) paramMap.get("data");
		// 1.大类信息查询所以的产品
		JSONArray bigTypeArr = new JSONArray();
		List<ProductInfo> list = productInfoMapper.selectListBigType(paramMap2);//根据大类id 查询所有数据
		for (ProductInfo productInfo:list){
			//遍历查询出每个小类信息，并封装
			ProductInfoVo tf=new ProductInfoVo();
			BeanUtils.copyProperties(productInfo,tf);
			List<Long> listSmallType = new ArrayList<Long>();
			List<SmallProductRelation> smallProductRelation = smallProductRelationMapper.selectByProductId(productInfo.getProductId());
			tf.setSmalltypes(listSmallType);
			for(SmallProductRelation slist:smallProductRelation){
				listSmallType.add(slist.getSmalltypeId());
				tf.setSmalltypes(listSmallType);
			}
			JSONObject bigJson = new JSONObject();
			bigJson.put("productInfo", tf);
			bigTypeArr.add(bigJson);

		}
		resJson.put("productInfoList",bigTypeArr);
		//返回小类列表信息
		//JSONArray smallTypeArr = new JSONArray();
		List<ProductSmallTypeInfo>  smallTypeList  = productSmallTypeInfoMapper.query(new ProductSmallTypeInfo());
		resJson.put("smallTypeList",smallTypeList);
		System.out.println(resJson.toJSONString());
		return resJson.toJSONString();
	}

	@Override
	public String queryInfo(Map<String, Object> paramMap) throws SysException {
		JSONObject resJson = new JSONObject();
		Map<String, Object> paramMap2 = (Map<String, Object>) paramMap.get("data");//参数产品ID
		//查询产品信息
		Integer id = (Integer) paramMap2.get("productId");
		ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(Long.valueOf(id));
		resJson.put("productInfo",productInfo);
		//查询大类信息
		ProductBigTypeInfo productBigTypeInfo = productBigTypeInfoMapper.selectByPrimaryKey(productInfo.getBigtypeId());
		resJson.put("bigTypeInfo",productBigTypeInfo);
		//查询小类信息
		List<SmallProductRelation> smallProductRelation = smallProductRelationMapper
							.selectByProductId(productInfo.getProductId());//根据产品ID查询出所以的小类关联ID
		JSONArray smallTypeList = new JSONArray();
		for (SmallProductRelation sm:smallProductRelation){
			//遍历获取小类信息
			ProductSmallTypeInfo ps = productSmallTypeInfoMapper.selectByPrimaryKey(sm.getSmalltypeId());
			JSONObject smallJson = new JSONObject();
			smallJson.put("smallTypeInfo", ps);
			smallTypeList.add(smallJson);
		}
		resJson.put("smallTypeList",smallTypeList);
		//查询介绍信息
		List<ProductInformationRelation> infomationList = productInformationRelationMapper.selectByProductId(productInfo.getProductId());
		JSONArray infomationInfoList = new JSONArray();
		for (ProductInformationRelation list :infomationList){
			ProductInformation productInformation = 	productInformationMapper.selectByPrimaryKey(list.getInformationId());
			JSONObject infomationJson = new JSONObject();
			infomationJson.put("infomationInfo", productInformation);
			infomationInfoList.add(infomationJson);
		}
		resJson.put("infomationInfoList",infomationInfoList);
		System.out.println(resJson.toJSONString());
		return resJson.toJSONString();
	}


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
