package com.xiandaojia.service.impl.product;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductInformation;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.product.ProductInformationMapper;
import com.xiandaojia.service.product.IProductInformationService;

@Service("productInformationService")
public class ProductInformationServiceImpl implements IProductInformationService {

	@Autowired
	ProductInformationMapper productInformationMapper;

	@Override
	public PaginationDto<ProductInformation> queryProductListByPage(int page, int pageSize, Integer totalCount)
			throws SysException {
		PaginationUtil.checkPaginationArgs(page, pageSize);
		PaginationDto<ProductInformation> paginationDto = new PaginationDto<ProductInformation>(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			totalCount = productInformationMapper.getTotalCount();
		}
		paginationDto.setTotalCount(totalCount);
		List<ProductInformation> list = productInformationMapper.queryListByPage(offset, pageSize);
		paginationDto.setData(list);
		return paginationDto;
	}

	@Override
	public void insert(ProductInformation t) {
		t.setCreateTime(new Date());
		productInformationMapper.insert(t);

	}

	@Override
	public void update(ProductInformation t) {
		t.setUpdateTime(new Date());
		productInformationMapper.updateByPrimaryKeySelective(t);

	}

	@Override
	public void delete(Long id) {
		productInformationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<ProductInformation> queryListByProductId(Long productId) {
		return productInformationMapper.queryListByProductId(productId);
	}

}
