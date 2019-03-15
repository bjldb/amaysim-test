package com.amaysim.shoppingcart.implem.rules.computation;

import java.util.Map;

import com.amaysim.shoppingcart.base.catalogue.Catalogue;
import com.amaysim.shoppingcart.base.catalogue.CatalogueProduct;
import com.amaysim.shoppingcart.base.rules.ComputationRule;

/**
 * This class represents represents the default computation rule:
 * Compute all items by quantity and unit price
 */
public class DefaultComputationRule extends ComputationRule {

	/* (non-Javadoc)
	 * @see com.amaysim.shoppingcart.rules.computation.ComputationRule#updateTotalAndCart(java.lang.Double, java.util.Map)
	 */
	@Override
	public Double updateTotalAndCart(Double currentTotal, Map<String, Integer> currentCart, Catalogue catalogue) {
		for(String productCode: currentCart.keySet()) {
			CatalogueProduct product = catalogue.getProduct(productCode);
			if(product != null) {
				int productQty = currentCart.get(productCode); 
				currentTotal += product.getPrice() * productQty;
			}
		}
		
		return currentTotal;
	}

	
	
}
