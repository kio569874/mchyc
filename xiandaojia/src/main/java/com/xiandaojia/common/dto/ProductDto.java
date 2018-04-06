package com.xiandaojia.common.dto;

import java.util.List;

import com.xiandaojia.common.domain.ProductInfo;
import com.xiandaojia.common.domain.ProductInformationRelation;
import com.xiandaojia.common.domain.SmallProductRelation;

public class ProductDto {

	private ProductInfo productInfo;

	private List<ProductInformationRelation> productInformationRelationList;

	private List<SmallProductRelation> smallProductRelationList;

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public List<ProductInformationRelation> getProductInformationRelationList() {
		return productInformationRelationList;
	}

	public void setProductInformationRelationList(List<ProductInformationRelation> productInformationRelationList) {
		this.productInformationRelationList = productInformationRelationList;
	}

	public List<SmallProductRelation> getSmallProductRelationList() {
		return smallProductRelationList;
	}

	public void setSmallProductRelationList(List<SmallProductRelation> smallProductRelationList) {
		this.smallProductRelationList = smallProductRelationList;
	}

}
