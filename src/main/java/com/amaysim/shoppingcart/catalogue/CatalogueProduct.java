package com.amaysim.shoppingcart.catalogue;

/**
 * This enum represents the catalogue of products that can be chosen for the shopping cart
 */
public enum CatalogueProduct {
	
	//           Product Code	Product Name		Unit Price 
	ULT_SMALL	("ult_small",	"Unlimited 1GB",	24.90),
	ULT_MEDIUM	("ult_medium",	"Unlimited 2GB",	29.90),
	ULT_LARGE	("ult_large",	"Unlimited 5GB",	44.90),
	ONE_GB		("1gb",			"1 GB Data-pack",	 9.90);
	
	private String productCode;
	private String productName;
	private Double price;
	
	/**
	 * Constructor
	 * @param productCode product code
	 * @param productName product name
	 * @param price product unit price
	 */
	private CatalogueProduct(String productCode, String productName, Double price) {
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
	}
	
	/**
	 * Returns product code
	 * @return product code
	 */
	public String productCode() {
		return this.productCode;
	}
	
	/**
	 * Returns product name
	 * @return product name
	 */
	public String productName() {
		return this.productName;
	}
	
	/**
	 * Returns product unit price
	 * @return product unit price
	 */
	public Double price() {
		return this.price;
	}

}
