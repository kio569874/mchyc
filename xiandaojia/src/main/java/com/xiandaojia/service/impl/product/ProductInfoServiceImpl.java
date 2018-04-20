package com.xiandaojia.service.impl.product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.domain.ProductInformationRelation;
import com.xiandaojia.common.domain.SmallProductRelation;
import com.xiandaojia.common.dto.ProductDto;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.product.ProductInfoMapper;
import com.xiandaojia.mapper.product.ProductInformationRelationMapper;
import com.xiandaojia.mapper.product.SmallProductRelationMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.product.IProductInfoService;

@Service("productInfoService")
public class ProductInfoServiceImpl extends AbstractBaseService implements IProductInfoService {

	@Autowired
	ProductInfoMapper productInfoMapper;
	@Autowired
	ProductInformationRelationMapper productInformationRelationMapper;
	@Autowired
	SmallProductRelationMapper smallProductRelationMapper;

	@Override
	public void insert(Object t) {
		ProductDto productDto = (ProductDto) t;
		ProductInfo productInfo = productDto.getProductInfo();
		productInfo.setCreateTime(new Date());
		productInfo.setVersion(0L);
		productInfoMapper.insert(productInfo);

		List<SmallProductRelation> smallProductRelationList = productDto.getSmallProductRelationList();
		if (smallProductRelationList != null && smallProductRelationList.size() > 0) {
			for (SmallProductRelation smallProductRelation : smallProductRelationList) {
				if (StringUtils.isEmpty(smallProductRelation.getProductId())) {
					smallProductRelation.setProductId(productInfo.getProductId());
				}
				smallProductRelationMapper.insert(smallProductRelation);
			}
		}

		List<ProductInformationRelation> productInformationRelationList = productDto
				.getProductInformationRelationList();
		if (productInformationRelationList != null && productInformationRelationList.size() > 0) {
			for (ProductInformationRelation productInformationRelation : productInformationRelationList) {
				if (StringUtils.isEmpty(productInformationRelation.getProductId())) {
					productInformationRelation.setProductId(productInfo.getProductId());
				}
				productInformationRelationMapper.insert(productInformationRelation);
			}
		}

	}

	@Override
	public void update(Object t) {
		ProductDto productDto = (ProductDto) t;
		ProductInfo productInfo = productDto.getProductInfo();
		productInfo.setUpdateTime(new Date());
		productInfoMapper.updateByPrimaryKeySelective(productInfo);
		// 修改产品属性关系信息，暂时先直接删除再插入
		List<SmallProductRelation> smallProductRelationList = productDto.getSmallProductRelationList();
		if (smallProductRelationList != null && smallProductRelationList.size() > 0) {
			smallProductRelationMapper.deleteByProductId(productInfo.getProductId());
			for (SmallProductRelation smallProductRelation : smallProductRelationList) {
				if (StringUtils.isEmpty(smallProductRelation.getProductId())) {
					smallProductRelation.setProductId(productInfo.getProductId());
				}
				smallProductRelationMapper.insert(smallProductRelation);
			}
		}
		// 修改产品介绍关系信息，暂时先直接删除再插入
		List<ProductInformationRelation> productInformationRelationList = productDto
				.getProductInformationRelationList();
		if (productInformationRelationList != null && productInformationRelationList.size() > 0) {
			productInformationRelationMapper.deleteByProductId(productInfo.getProductId());
			for (ProductInformationRelation productInformationRelation : productInformationRelationList) {
				if (StringUtils.isEmpty(productInformationRelation.getProductId())) {
					productInformationRelation.setProductId(productInfo.getProductId());
				}
				productInformationRelationMapper.insert(productInformationRelation);
			}
		}

	}

	@Override
	public void delete(Long id) {
		productInfoMapper.deleteByPrimaryKey(id);
		smallProductRelationMapper.deleteByProductId(id);
		productInformationRelationMapper.deleteByProductId(id);

	}

	@Override
	public PaginationDto<ProductInfo> queryProductListByPage(int page, int pageSize, Integer totalCount,
			Map<String, Object> paramMap) throws SysException {
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
		return paginationDto;
	}

}
