package com.amaysim.shoppingcart.rules.computation;

import java.util.Map;

import com.amaysim.shoppingcart.catalogue.CatalogueProduct;

/**
 * This class represents represents the computation rule:
 * "The Unlimited 5GB Sim will have a bulk discount applied;
 * whereby the price will drop to $39.90 each for the first month, if the customer buys more than 3."
 */
public class Unli5GBComputationRule extends ComputationRule {

	/* (non-Javadoc)
	 * @see com.amaysim.shoppingcart.rules.computation.ComputationRule#updateTotalAndCart(java.lang.Double, java.util.Map)
	 */
	@Override
	public Double updateTotalAndCart(Double currentTotal, Map<CatalogueProduct, Integer> currentCart) {
		
		if(currentCart.containsKey(CatalogueProduct.ULT_LARGE)) {
			
			int unli5GBQty = currentCart.get(CatalogueProduct.ULT_LARGE);
			if(unli5GBQty > 3) {
				currentTotal += 39.90 * unli5GBQty;
				currentCart.remove(CatalogueProduct.ULT_LARGE);
			}
		}
		
		return currentTotal;
	}

	
	
}
