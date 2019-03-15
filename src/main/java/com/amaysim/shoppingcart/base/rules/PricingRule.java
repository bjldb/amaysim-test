package com.amaysim.shoppingcart.base.rules;

import com.amaysim.shoppingcart.base.catalogue.Catalogue;

/**
 * This abstract rule represents pricing rules that may be applied to cart items
 */
public abstract class PricingRule {
	
	private Catalogue catalogue;
	
	/**
	 * This enum represents the types of pricing rules that may used over cart items
	 */
	public enum RuleType {
		BUNDLING, COMPUTATION, FINALIZATION
	}
	
	private RuleType ruleType;
	
	/**
	 * Constructor
	 * @param ruleType rule type for this pricing rule
	 */
	public PricingRule(RuleType ruleType) {
		this.ruleType = ruleType;
	}
	
	/**
	 * Returns the rule type for this pricing rule
	 * @return the rule type for this pricing rule
	 */
	public RuleType getRuleType() {
		return this.ruleType;
	}

}
