package com.amaysim.shoppingcart.rules.computation;

import java.util.Map;

import com.amaysim.shoppingcart.catalogue.CatalogueProduct;
import com.amaysim.shoppingcart.rules.PricingRule;

/**
 * This abstract class represents a variant of the pricing rule which involves computing cart total based on specific offers or promotions
 */
public abstract class ComputationRule extends PricingRule {

	/**
	 * Constructor
	 */
	public ComputationRule() {
		super(PricingRule.RuleType.COMPUTATION);
	}
	
	/**
	 * Updates current cart total based on the offer or promo pricing rule
	 * and removes from the cart those products that have been included in the computation
	 * @param currentTotal running cart total before the pricing rule is applied
	 * @param currentCart current cart products not yet included in the computation
	 * @return running cart total after the pricing rule is applied
	 */
	public abstract Double updateTotalAndCart(Double currentTotal, Map<CatalogueProduct,Integer> currentCart);

}
