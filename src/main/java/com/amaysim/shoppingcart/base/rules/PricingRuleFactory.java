package com.amaysim.shoppingcart.base.rules;

import java.util.HashMap;
import java.util.Map;

import com.amaysim.shoppingcart.implem.rules.bundling.Unli2GBundlingRule;
import com.amaysim.shoppingcart.implem.rules.computation.Unli1GBComputationRule;
import com.amaysim.shoppingcart.implem.rules.computation.Unli5GBComputationRule;
import com.amaysim.shoppingcart.implem.rules.finalization.Discount10PercentFinalizationRule;

/**
 * This class represents the factory of applicable pricing rules for the shopping cart
 */
public class PricingRuleFactory {
	
	private static Map<String,PricingRule> rules;
	
	/**
	 * Static initializer for this factory
	 */
	static {
		rules = new HashMap<>();
		rules.put("Unli2GBBundle", new Unli2GBundlingRule());
		rules.put("Unli1GBDeal", new Unli1GBComputationRule());
		rules.put("Unli5GBBulk", new Unli5GBComputationRule());
		rules.put("I<3AMAYSIM", new Discount10PercentFinalizationRule());
	}
	
	/**
	 * Returns the pricing rule object for the given pricing code 
	 * @param code pricing code representing the pricing rule
	 * @return the pricing rule object for the given pricing code 
	 */
	public static PricingRule getRuleForCode(String code) {
		return rules.get(code);
	}

}
