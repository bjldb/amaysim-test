package com.amaysim.shoppingcart.rules.finalization;

import com.amaysim.shoppingcart.rules.PricingRule;

/**
 * This abstract class represents a variant of the pricing rule which involves modifications of total price after computation on cart items  
 */
public abstract class FinalizationRule extends PricingRule {

	/**
	 * Constructor
	 */
	public FinalizationRule() {
		super(PricingRule.RuleType.FINALIZATION);
	}
	
	/**
	 * Updates the running cart total based on pricing rule
	 * @param currentTotal running cart total
	 * @return updated running cart total
	 */
	public abstract Double updateTotal(Double currentTotal);

	
}
