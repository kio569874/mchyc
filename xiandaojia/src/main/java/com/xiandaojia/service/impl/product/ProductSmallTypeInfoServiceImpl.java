package com.xiandaojia.service.impl.product;

import java.util.List;

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
		productSmallTypeInfoMapper.insert(t);

	}

	@Override
	public void update(ProductSmallTypeInfo t) {
		productSmallTypeInfoMapper.updateByPrimaryKey(t);

	}

	@Override
	public void delete(Long id) {
		productSmallTypeInfoMapper.deleteByPrimaryKey(id);

	}

	@Override
	public PaginationDto<ProductSmallTypeInfo> queryOrderListByPage(int page, int pageSize, Integer totalCount,
			Long bigtypeId) throws SysException {
		PaginationDto<ProductSmallTypeInfo> paginationDto = new PaginationDto<ProductSmallTypeInfo>();
		PaginationUtil.checkPaginationArgs(page, pageSize);
		int offset = (page - 1) * pageSize;
		if (totalCount == null || totalCount <= 0) {
			paginationDto.setTotalCount(productSmallTypeInfoMapper.getTotalCount(bigtypeId));
		}
		List<ProductSmallTypeInfo> userEntityList = productSmallTypeInfoMapper.queryListByPage(offset, pageSize,
				bigtypeId);
		paginationDto.setData(userEntityList);
		return paginationDto;
	}

}
