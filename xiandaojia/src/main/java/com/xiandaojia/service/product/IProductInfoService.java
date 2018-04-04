package com.xiandaojia.service.product;

import java.util.List;

import com.xiandaojia.common.domain.ProductInformation;

public interface IProductInfoService extends IProductService {
	
	List<ProductInformation> queryListByProductId(Long productId);

}
