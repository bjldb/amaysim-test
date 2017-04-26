package com.amaysim.shoppingcart.rules.computation;

import java.util.Map;

import com.amaysim.shoppingcart.catalogue.CatalogueProduct;

/**
 * This class represents represents the default computation rule:
 * Compute all items by quantity and unit price
 */
public class DefaultComputationRule extends ComputationRule {

	/* (non-Javadoc)
	 * @see com.amaysim.shoppingcart.rules.computation.ComputationRule#updateTotalAndCart(java.lang.Double, java.util.Map)
	 */
	@Override
	public Double updateTotalAndCart(Double currentTotal, Map<CatalogueProduct, Integer> currentCart) {
		// default rule
		for(CatalogueProduct product: currentCart.keySet()) {
			int productQty = currentCart.get(product); 
			currentTotal += product.price() * productQty;
			currentCart.remove(product);
		}
		
		return currentTotal;
	}

	
	
}
