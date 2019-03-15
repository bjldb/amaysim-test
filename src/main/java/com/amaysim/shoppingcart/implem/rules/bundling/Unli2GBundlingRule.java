package com.amaysim.shoppingcart.implem.rules.bundling;

import java.util.Map;

import com.amaysim.shoppingcart.base.catalogue.CatalogueProduct;
import com.amaysim.shoppingcart.base.rules.BundlingRule;

/**
 * This class represents the bundling rule for Unlimited 1GB product:
 * "We will bundle in a free 1 GB Data-pack free-of-charge with every Unlimited 2GB sold"
 */
public class Unli2GBundlingRule extends BundlingRule {
	
	private final String ONE_GB = "1gb";
	private final String ULT_MEDIUM = "ult_medium";
	
	/* (non-Javadoc)
	 * @see com.amaysim.shoppingcart.rules.bundling.BundlingRule#updateCartForPricing(java.util.Map)
	 */
	@Override
	public void updateCartForPricing(Map<String,Integer> cartProducts) {
		
		if(cartProducts.containsKey(ONE_GB) && cartProducts.containsKey(ULT_MEDIUM)) {
			int oneGBQty = cartProducts.get(ONE_GB);
			int unli2GBQty = cartProducts.get(ULT_MEDIUM);
			if(oneGBQty > unli2GBQty) {
				cartProducts.put(ONE_GB, oneGBQty - unli2GBQty);
			} else {
				cartProducts.remove(ONE_GB);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.amaysim.shoppingcart.rules.bundling.BundlingRule#updateCartForItemization(java.util.Map)
	 */
	@Override
	public void updateCartForItemization(Map<String,Integer> cartProducts) {
		
		if(cartProducts.containsKey(ULT_MEDIUM)) {
			Integer unli2GB = cartProducts.get(ULT_MEDIUM);
			Integer dpack1GB = cartProducts.get(ONE_GB);
			if(dpack1GB == null || unli2GB > dpack1GB) {
				cartProducts.put(ONE_GB, unli2GB);
			}
		}
		
	}
	

}
