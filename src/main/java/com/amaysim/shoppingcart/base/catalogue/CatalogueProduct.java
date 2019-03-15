package com.amaysim.shoppingcart.base.catalogue;

public class CatalogueProduct {
	
	private String productCode;
	private String productName;
	private Double price;
	
	public CatalogueProduct(String productCode, String productName, Double price) {
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
	}
	
	public String getProductCode() {
		return productCode;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public Double getPrice() {
		return price;
	}
}
