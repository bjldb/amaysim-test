package com.amaysim.shoppingcart.implem.rules.computation;

import java.util.Map;

import com.amaysim.shoppingcart.base.catalogue.Catalogue;
import com.amaysim.shoppingcart.base.catalogue.CatalogueProduct;
import com.amaysim.shoppingcart.base.rules.ComputationRule;

/**
 * This class represents represents the computation rule:
 * "The Unlimited 5GB Sim will have a bulk discount applied;
 * whereby the price will drop to $39.90 each for the first month, if the customer buys more than 3."
 */
public class Unli5GBComputationRule extends ComputationRule {

	private final String ULT_LARGE = "ult_large";

	/* (non-Javadoc)
	 * @see com.amaysim.shoppingcart.rules.computation.ComputationRule#updateTotalAndCart(java.lang.Double, java.util.Map)
	 */
	@Override
	public Double updateTotalAndCart(Double currentTotal, Map<String, Integer> currentCart, Catalogue catalogue) {	
		CatalogueProduct product = catalogue.getProduct(ULT_LARGE);
		if(product != null && currentCart.containsKey(ULT_LARGE)) {
			int unli5GBQty = currentCart.get(ULT_LARGE);
			if(unli5GBQty > 3) {
				currentTotal += 39.90 * unli5GBQty;
				currentCart.remove(ULT_LARGE);
			}
		}
		
		return currentTotal;
	}

	
	
}
