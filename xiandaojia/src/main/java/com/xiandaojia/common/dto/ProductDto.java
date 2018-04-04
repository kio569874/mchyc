package com.xiandaojia.common.dto;

import java.util.List;

import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.domain.ProductInformation;

public class ProductDto {

	private ProductInfo productInfo;

	private List<ProductInformation> productInformationList;

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public List<ProductInformation> getProductInformationList() {
		return productInformationList;
	}

	public void setProductInformationList(List<ProductInformation> productInformationList) {
		this.productInformationList = productInformationList;
	}

}
