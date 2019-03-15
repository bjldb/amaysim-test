package com.amaysim.shoppingcart.base.rules;

import java.util.Map;

/**
 * This abstract class represents a variant of the pricing rule which involves product bundling based on specific offers or promotions
 */
public abstract class BundlingRule extends PricingRule {
	
	/**
	 * Constructor
	 */
	public BundlingRule() {
		super(PricingRule.RuleType.BUNDLING);
	}

	/**
	 * Updates cart content in preparation for computation (removes free-of-charge items)
	 * @param cartProducts map of products to be updated
	 */
	public abstract void updateCartForPricing(Map<String,Integer> cartProducts);
	
	/**
	 * Updates cart content in preparation for full itemization (includes free-of-charge items)
	 * @param cartProducts map of products to be updated
	 */
	public abstract void updateCartForItemization(Map<String,Integer> cartProducts);
	
}
