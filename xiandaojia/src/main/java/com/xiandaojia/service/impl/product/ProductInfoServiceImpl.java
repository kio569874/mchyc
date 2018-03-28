package com.xiandaojia.service.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.product.ProductInfoMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.product.IProductInfoService;

@Service("productInfoService")
public class ProductInfoServiceImpl extends AbstractBaseService implements IProductInfoService {

	@Autowired
	ProductInfoMapper productInfoMapper;

	@Override
	public void insert(ProductInfo t) {
		productInfoMapper.insert(t);

	}

	@Override
	public void update(ProductInfo t) {
		productInfoMapper.updateByPrimaryKey(t);

	}

	@Override
	public void delete(Long id) {
		productInfoMapper.deleteByPrimaryKey(id);

	}

	@Override
	public PaginationDto<ProductInfo> queryOrderListByPage(int page, int pageSize, Integer totalCount, Long smalltypeId)
			throws SysException {
		PaginationDto<ProductInfo> paginationDto = new PaginationDto<ProductInfo>();
		// 分页参数校验
		PaginationUtil.checkPaginationArgs(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			paginationDto.setTotalCount(productInfoMapper.getTotalCount(smalltypeId));
		}
		List<ProductInfo> userEntityList = productInfoMapper.queryListByPage(offset, pageSize, smalltypeId);
		paginationDto.setData(userEntityList);
		return paginationDto;
	}

}
