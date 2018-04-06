package com.xiandaojia.controller.product;

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
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductBigTypeInfo;
import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.domain.ProductInformation;
import com.xiandaojia.common.domain.ProductSmallTypeInfo;
import com.xiandaojia.common.dto.ProductDto;
import com.xiandaojia.common.utils.JsonBeanUtil;
import com.xiandaojia.controller.BaseController;
import com.xiandaojia.service.product.IProductBigTypeInfoService;
import com.xiandaojia.service.product.IProductInfoService;
import com.xiandaojia.service.product.IProductInformationService;
import com.xiandaojia.service.product.IProductSmallTypeInfoService;

/**
 * 产品服务
 * 
 * @author mchyc
 *
 */
@RestController
@RequestMapping("product")
public class ProductController extends BaseController {

	@Autowired
	private IProductInfoService productInfoService;
	@Autowired
	private IProductBigTypeInfoService productBigTypeInfoService;
	@Autowired
	private IProductSmallTypeInfoService productSmallTypeInfoService;
	@Autowired
	private IProductInformationService productInformationService;

	@RequestMapping(value = "/productInfo/insert", method = RequestMethod.POST)
	@ResponseBody
	public String productInfoInsert(@RequestBody String content) {
		try {
			ProductDto productDto = JsonBeanUtil.stringToBean(ProductDto.class, content);
			productInfoService.insert(productDto);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productInfo/delete", method = RequestMethod.POST)
	@ResponseBody
	public String productInfoDelete(@RequestBody String content) {
		try {
			ProductInfo productInfo = JsonBeanUtil.stringToBean(ProductInfo.class, content);
			productInfoService.delete(productInfo.getProductId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productInfo/update", method = RequestMethod.POST)
	@ResponseBody
	public String productInfoUpdate(@RequestBody String content) {
		try {
			ProductDto productInfo = JsonBeanUtil.stringToBean(ProductDto.class, content);
			productInfoService.update(productInfo);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productInfo/query", method = RequestMethod.POST)
	@ResponseBody
	public String productInfoQuery(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			Map<String, Object> paramMap = jsonObj;
			PaginationDto<ProductInfo> paginationDto = productInfoService.queryProductListByPage(page, pageSize, null,
					paramMap);
			List<ProductInfo> productInfoList = paginationDto.getData();
			if (productInfoList != null && productInfoList.size() > 0) {
				JSONObject list = new JSONObject();
				JSONArray jsonArr = (JSONArray) JSONArray.toJSON(productInfoList);
				list.put("listData", jsonArr);
				return getSuccessResultMsg(list.toJSONString());
			} else {
				return getSuccessResultMsg(new JSONObject().toJSONString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productBigTypeInfo/insert", method = RequestMethod.POST)
	@ResponseBody
	public String productBigTypeInfoInsert(@RequestBody String content) {
		try {
			ProductBigTypeInfo productBigTypeInfo = JsonBeanUtil.stringToBean(ProductBigTypeInfo.class, content);
			productBigTypeInfoService.insert(productBigTypeInfo);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productBigTypeInfo/delete", method = RequestMethod.POST)
	@ResponseBody
	public String productBigTypeInfoDelete(@RequestBody String content) {
		try {
			ProductBigTypeInfo productBigTypeInfo = JsonBeanUtil.stringToBean(ProductBigTypeInfo.class, content);
			productBigTypeInfoService.delete(productBigTypeInfo.getBigtypeId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productBigTypeInfo/update", method = RequestMethod.POST)
	@ResponseBody
	public String productBigTypeInfoUpdate(@RequestBody String content) {
		try {
			ProductBigTypeInfo productBigTypeInfo = JsonBeanUtil.stringToBean(ProductBigTypeInfo.class, content);
			productBigTypeInfoService.update(productBigTypeInfo);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productBigTypeInfo/query", method = RequestMethod.POST)
	@ResponseBody
	public String productBigTypeInfoQuery(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			PaginationDto<ProductBigTypeInfo> paginationDto = productBigTypeInfoService.queryProductListByPage(page,
					pageSize, null);
			List<ProductBigTypeInfo> productBigTypeInfoList = paginationDto.getData();
			if (productBigTypeInfoList != null && productBigTypeInfoList.size() > 0) {
				JSONObject list = new JSONObject();
				JSONArray jsonArr = (JSONArray) JSONArray.toJSON(productBigTypeInfoList);
				list.put("listData", jsonArr);
				return getSuccessResultMsg(list.toJSONString());
			} else {
				return getSuccessResultMsg(new JSONObject().toJSONString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productSmallTypeInfo/insert", method = RequestMethod.POST)
	@ResponseBody
	public String productSmallTypeInfoInsert(@RequestBody String content) {
		try {
			ProductSmallTypeInfo productSmallTypeInfo = JsonBeanUtil.stringToBean(ProductSmallTypeInfo.class, content);
			productSmallTypeInfoService.insert(productSmallTypeInfo);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productSmallTypeInfo/delete", method = RequestMethod.POST)
	@ResponseBody
	public String productSmallTypeInfoDelete(@RequestBody String content) {
		try {
			ProductSmallTypeInfo productSmallTypeInfo = JsonBeanUtil.stringToBean(ProductSmallTypeInfo.class, content);
			productSmallTypeInfoService.delete(productSmallTypeInfo.getSmalltypeId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productSmallTypeInfo/update", method = RequestMethod.POST)
	@ResponseBody
	public String productSmallTypeInfoUpdate(@RequestBody String content) {
		try {
			ProductSmallTypeInfo productSmallTypeInfo = JsonBeanUtil.stringToBean(ProductSmallTypeInfo.class, content);
			productSmallTypeInfoService.update(productSmallTypeInfo);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productSmallTypeInfo/query", method = RequestMethod.POST)
	@ResponseBody
	public String productSmallTypeInfoQuery(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			PaginationDto<ProductSmallTypeInfo> paginationDto = productSmallTypeInfoService.queryProductListByPage(page,
					pageSize, null);
			List<ProductSmallTypeInfo> productSmallTypeInfoList = paginationDto.getData();
			if (productSmallTypeInfoList != null && productSmallTypeInfoList.size() > 0) {
				JSONObject list = new JSONObject();
				JSONArray jsonArr = (JSONArray) JSONArray.toJSON(productSmallTypeInfoList);
				list.put("listData", jsonArr);
				return getSuccessResultMsg(list.toJSONString());
			} else {
				return getSuccessResultMsg(new JSONObject().toJSONString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productInformation/insert", method = RequestMethod.POST)
	@ResponseBody
	public String productInformationInsert(@RequestBody String content) {
		try {
			ProductInformation productInformation = JsonBeanUtil.stringToBean(ProductInformation.class, content);
			productInformationService.insert(productInformation);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productInformation/delete", method = RequestMethod.POST)
	@ResponseBody
	public String productInformationDelete(@RequestBody String content) {
		try {
			ProductInformation productInformation = JsonBeanUtil.stringToBean(ProductInformation.class, content);
			productInformationService.delete(productInformation.getInformationId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/productInformation/update", method = RequestMethod.POST)
	@ResponseBody
	public String productInformationUpdate(@RequestBody String content) {
		try {
			ProductInformation productInformation = JsonBeanUtil.stringToBean(ProductInformation.class, content);
			productInformationService.update(productInformation);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	/**
	 * 介绍信息本身分页查询
	 * 
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/productInformation/query", method = RequestMethod.POST)
	@ResponseBody
	public String productInformationQuery(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			PaginationDto<ProductInformation> paginationDto = productInformationService.queryProductListByPage(page,
					pageSize, null);
			List<ProductInformation> productInformationList = paginationDto.getData();
			if (productInformationList != null && productInformationList.size() > 0) {
				JSONObject list = new JSONObject();
				JSONArray jsonArr = (JSONArray) JSONArray.toJSON(productInformationList);
				list.put("listData", jsonArr);
				return getSuccessResultMsg(list.toJSONString());
			} else {
				return getSuccessResultMsg(new JSONObject().toJSONString());
			}
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
	@RequestMapping(value = "/productInformation/queryByProductId", method = RequestMethod.POST)
	@ResponseBody
	public String productInformationQureyByProductId(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			Long productId = jsonObj.getLong("productId");
			List<ProductInformation> productInformationList = productInformationService.queryListByProductId(productId);
			if (productInformationList != null && productInformationList.size() > 0) {
				JSONObject list = new JSONObject();
				JSONArray jsonArr = (JSONArray) JSONArray.toJSON(productInformationList);
				list.put("listData", jsonArr);
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
