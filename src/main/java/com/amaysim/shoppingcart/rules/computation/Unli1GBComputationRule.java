package com.amaysim.shoppingcart.rules.computation;

import java.util.Map;

import com.amaysim.shoppingcart.catalogue.CatalogueProduct;

/**
 * This class represents represents the computation rule:
 * "A 3 for 2 deal on Unlimited 1GB Sims.
 * So for example, if you buy 3 Unlimited 1GB Sims, you will pay the price of 2 only for the first month."
 */
public class Unli1GBComputationRule extends ComputationRule {

	@Override
	public Double updateTotalAndCart(Double currentTotal, Map<CatalogueProduct, Integer> currentCart) {
		
		if(currentCart.containsKey(CatalogueProduct.ULT_SMALL)) {
			int unli1GBQty = currentCart.get(CatalogueProduct.ULT_SMALL);
			int freeQty = unli1GBQty / 3;
			
			int payableQty = unli1GBQty - freeQty; 
			
			currentTotal += CatalogueProduct.ULT_SMALL.price() * payableQty;
			currentCart.remove(CatalogueProduct.ULT_SMALL);
		}
		
		return currentTotal;
	}

}
