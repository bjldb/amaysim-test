package com.amaysim.shoppingcart.rules.bundling;

import java.util.Map;

import com.amaysim.shoppingcart.catalogue.CatalogueProduct;

/**
 * This class represents the bundling rule for Unlimited 1GB product:
 * "We will bundle in a free 1 GB Data-pack free-of-charge with every Unlimited 2GB sold"
 */
public class Unli2GBundlingRule extends BundlingRule {
	
	/* (non-Javadoc)
	 * @see com.amaysim.shoppingcart.rules.bundling.BundlingRule#updateCartForPricing(java.util.Map)
	 */
	@Override
	public void updateCartForPricing(Map<CatalogueProduct,Integer> cartProducts) {
		
		if(cartProducts.containsKey(CatalogueProduct.ONE_GB) && cartProducts.containsKey(CatalogueProduct.ULT_MEDIUM)) {
			int oneGBQty = cartProducts.get(CatalogueProduct.ONE_GB);
			int unli2GBQty = cartProducts.get(CatalogueProduct.ULT_MEDIUM);
			if(oneGBQty > unli2GBQty) {
				cartProducts.put(CatalogueProduct.ONE_GB, oneGBQty - unli2GBQty);
			} else {
				cartProducts.remove(CatalogueProduct.ONE_GB);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.amaysim.shoppingcart.rules.bundling.BundlingRule#updateCartForItemization(java.util.Map)
	 */
	@Override
	public void updateCartForItemization(Map<CatalogueProduct,Integer> cartProducts) {
		
		if(cartProducts.containsKey(CatalogueProduct.ULT_MEDIUM)) {
			Integer unli2GB = cartProducts.get(CatalogueProduct.ULT_MEDIUM);
			Integer dpack1GB = cartProducts.get(CatalogueProduct.ONE_GB);
			if(dpack1GB == null || unli2GB > dpack1GB) {
				cartProducts.put(CatalogueProduct.ONE_GB, unli2GB);
			}
		}
		
	}
	

}
