package com.xiandaojia.service.product;

import java.util.List;

import com.xiandaojia.common.domain.ProductInformation;

public interface IProductInformationService extends IProductService<ProductInformation>{
	
	List<ProductInformation> queryListByProductId(Long productId);
}
