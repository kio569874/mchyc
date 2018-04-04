package com.xiandaojia.service.impl.product;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductBigTypeInfo;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.product.ProductBigTypeInfoMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.product.IProductBigTypeInfoService;

@Service("productBigTypeInfoService")
public class ProductBigTypeInfoServiceImpl extends AbstractBaseService implements IProductBigTypeInfoService {

	@Resource
	ProductBigTypeInfoMapper productBigTypeInfoMapper;

	@Override
	public void insert(ProductBigTypeInfo t) {
		t.setCreateTime(new Date());
		productBigTypeInfoMapper.insert(t);
	}

	@Override
	public void update(ProductBigTypeInfo t) {
		t.setUpdateTime(new Date());
		productBigTypeInfoMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public void delete(Long id) {
		productBigTypeInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PaginationDto<ProductBigTypeInfo> queryProductListByPage(int page, int pageSize, Integer totalCount,
			Long smalltypeId) throws SysException {
		PaginationDto<ProductBigTypeInfo> paginationDto = new PaginationDto<ProductBigTypeInfo>();
		// 分页参数校验
		PaginationUtil.checkPaginationArgs(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			paginationDto.setTotalCount(productBigTypeInfoMapper.getTotalCount());
		}
		List<ProductBigTypeInfo> list = productBigTypeInfoMapper.queryListByPage(offset, pageSize);
		paginationDto.setData(list);
		return paginationDto;
	}

}
