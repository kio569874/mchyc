package com.xiandaojia.service.impl.product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.domain.ProductSmallTypeInfo;
import com.xiandaojia.common.exception.SysException;
import com.xiandaojia.common.utils.PaginationUtil;
import com.xiandaojia.mapper.product.ProductSmallTypeInfoMapper;
import com.xiandaojia.service.impl.AbstractBaseService;
import com.xiandaojia.service.product.IProductSmallTypeInfoService;

@Service("productSmallTypeInfoService")
public class ProductSmallTypeInfoServiceImpl extends AbstractBaseService implements IProductSmallTypeInfoService {

	@Autowired
	ProductSmallTypeInfoMapper productSmallTypeInfoMapper;

	@Override
	public void insert(ProductSmallTypeInfo t) {
		t.setCreateTime(new Date());
		productSmallTypeInfoMapper.insert(t);

	}

	@Override
	public void update(ProductSmallTypeInfo t) {
		t.setUpdateTime(new Date());
		productSmallTypeInfoMapper.updateByPrimaryKeySelective(t);

	}

	@Override
	public void delete(Long id) {
		productSmallTypeInfoMapper.deleteByPrimaryKey(id);

	}

	@Override
	public PaginationDto<ProductSmallTypeInfo> queryProductListByPage(int page, int pageSize, Integer totalCount,
			Map<String, Object> paramMap) throws SysException {
		PaginationUtil.checkPaginationArgs(page, pageSize);
		PaginationDto<ProductSmallTypeInfo> paginationDto = new PaginationDto<ProductSmallTypeInfo>(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			totalCount = productSmallTypeInfoMapper.getTotalCount();
		}
		paginationDto.setTotalCount(totalCount);
		List<ProductSmallTypeInfo> list = productSmallTypeInfoMapper.queryListByPage(offset, pageSize);
		paginationDto.setData(list);
		return paginationDto;
	}

}
