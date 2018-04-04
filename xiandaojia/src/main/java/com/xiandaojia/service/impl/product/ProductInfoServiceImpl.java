package com.xiandaojia.service.impl.product;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.domain.ProductInformation;
import com.xiandaojia.common.dto.ProductDto;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.product.ProductInfoMapper;
import com.xiandaojia.mapper.product.ProductInformationMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.product.IProductInfoService;

@Service("productInfoService")
public class ProductInfoServiceImpl extends AbstractBaseService implements IProductInfoService {

	@Autowired
	ProductInfoMapper productInfoMapper;
	@Autowired
	ProductInformationMapper productInformationMapper;

	@Override
	public void insert(Object t) {
		ProductDto productDto = (ProductDto) t;
		ProductInfo productInfo = productDto.getProductInfo();
		productInfo.setCreateTime(new Date());
		productInfo.setVersion(0L);
		productInfoMapper.insert(productInfo);
		List<ProductInformation> list = productDto.getProductInformationList();
		if (list != null && list.size() > 0) {
			for (ProductInformation productInformation : list) {
				productInformation.setCreateTime(new Date());
				if (StringUtils.isEmpty(productInformation.getProductId())) {
					productInformation.setProductId(productInfo.getProductId());
				}
				productInformationMapper.insert(productInformation);
			}
		}

	}

	@Override
	public void update(Object t) {
		ProductDto productDto = (ProductDto) t;
		ProductInfo productInfo = productDto.getProductInfo();
		productInfo.setUpdateTime(new Date());
		productInfoMapper.updateByPrimaryKeySelective(productInfo);
		List<ProductInformation> list = productDto.getProductInformationList();
		if (list != null && list.size() > 0) {
			for (ProductInformation productInformation : list) {
				productInformation.setUpdateTime(new Date());
				productInformationMapper.updateByPrimaryKeySelective(productInformation);
			}
		}
	}

	@Override
	public void delete(Long id) {
		productInfoMapper.deleteByPrimaryKey(id);
		productInformationMapper.deleteByProductId(id);

	}

	@Override
	public PaginationDto<ProductInfo> queryProductListByPage(int page, int pageSize, Integer totalCount, Long smalltypeId)
			throws SysException {
		PaginationDto<ProductInfo> paginationDto = new PaginationDto<ProductInfo>();
		// 分页参数校验
		PaginationUtil.checkPaginationArgs(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			paginationDto.setTotalCount(productInfoMapper.getTotalCount(smalltypeId));
		}
		List<ProductInfo> list = productInfoMapper.queryListByPage(offset, pageSize, smalltypeId);
		paginationDto.setData(list);
		return paginationDto;
	}

	@Override
	public List<ProductInformation> queryListByProductId(Long productId) {
		return productInformationMapper.queryListByProductId(productId);
	}
}
