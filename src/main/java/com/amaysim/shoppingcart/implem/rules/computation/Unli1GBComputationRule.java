package com.amaysim.shoppingcart.implem.rules.computation;

import java.util.Map;

import com.amaysim.shoppingcart.base.catalogue.Catalogue;
import com.amaysim.shoppingcart.base.catalogue.CatalogueProduct;
import com.amaysim.shoppingcart.base.rules.ComputationRule;

/**
 * This class represents represents the computation rule:
 * "A 3 for 2 deal on Unlimited 1GB Sims.
 * So for example, if you buy 3 Unlimited 1GB Sims, you will pay the price of 2 only for the first month."
 */
public class Unli1GBComputationRule extends ComputationRule {

	private final String ULT_SMALL = "ult_small";

	@Override
	public Double updateTotalAndCart(Double currentTotal, Map<String, Integer> currentCart, Catalogue catalogue) {
		CatalogueProduct product = catalogue.getProduct(ULT_SMALL);
		if(product != null && currentCart.containsKey(ULT_SMALL)) {
			int unli1GBQty = currentCart.get(ULT_SMALL);
			int freeQty = unli1GBQty / 3;
			
			int payableQty = unli1GBQty - freeQty; 
			
			currentTotal += product.getPrice() * payableQty;
			currentCart.remove(ULT_SMALL);
		}
		
		return currentTotal;
	}

}
