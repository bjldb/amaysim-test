package com.amaysim.shoppingcart.base.catalogue;

import java.util.HashMap;
import java.util.Map;

public class Catalogue {
	public Map<String, CatalogueProduct> products;
	
	public Catalogue() {
		this.products = new HashMap<>();
	}

	public Catalogue(Map<String, CatalogueProduct> products) {
		this.products = products;
	}
	
	public CatalogueProduct getProduct(String productCode) {
		return this.products.get(productCode);
	}
	
	public boolean hasProduct(String productCode) {
		return this.products.containsKey(productCode);
	}

	public void addProduct(CatalogueProduct product) {
		this.products.put(product.getProductCode(), product);
	}
}
