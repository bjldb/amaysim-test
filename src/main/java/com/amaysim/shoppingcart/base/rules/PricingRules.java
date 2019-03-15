package com.amaysim.shoppingcart.base.rules;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.amaysim.shoppingcart.base.catalogue.Catalogue;
import com.amaysim.shoppingcart.base.catalogue.ICatalogueProvider;
import com.amaysim.shoppingcart.implem.rules.computation.DefaultComputationRule;
import com.amaysim.shoppingcart.implem.rules.finalization.FinalizationRule;

/**
 * This class represents the set of pricing rules applicable to shopping cart items
 */
public class PricingRules {
	
	private Catalogue catalogue;
	private Map<PricingRule.RuleType,HashSet<PricingRule>> ruleSets;
	
	/**
	 * Constructor
	 * @throws Exception 
	 */
	public PricingRules(ICatalogueProvider provider) throws Exception {
		this.catalogue = provider.provideCatalogue();
		this.ruleSets = new EnumMap<>(PricingRule.RuleType.class);
		for(PricingRule.RuleType ruleType : PricingRule.RuleType.values()) {
			ruleSets.put(ruleType, new HashSet<>());
		}
	}
	
	public Catalogue getCatalogue() {
		return this.catalogue;
	}
	
	/**
	 * Adds pricing rule to the rule set
	 * @param rule pricing rule to add to rule set
	 */
	public void addRule(PricingRule rule) {
		if(rule != null && !this.ruleSets.get(rule.getRuleType()).contains(rule)) {
			this.ruleSets.get(rule.getRuleType()).add(rule);
		}
	}

	/**
	 * Computes the cart total price based on cart product content and promo codes to be applied
	 * @param cartProducts cart product content
	 * @param promoCodes promo codes to be applied
	 * @return total computed price
	 */
	public Double computeTotalPrice(final Map<String,Integer> cartProducts, Set<String> promoCodes) {
		
		Map<String,Integer> currentCart = new HashMap<>(cartProducts);
		
		// 0. update rules with promo codes
		this.updateRulesForPromoCodes(promoCodes);
		
		// 1. bundling rules
		this.executeBundlingRulesForPricing(currentCart);
		
		// 2. computation rules
		Double total = this.executeComputationRules(currentCart);
		
		// 3. finalization rules
		total = this.executeFinalizationRules(total);
		
		return total;
	}
	
	/**
	 * Generates the fully itemized cart after applying bundling rules
	 * @param cartProducts cart product content
	 * @return the fully itemized cart after applying bundling rules
	 */
	public Map<String,Integer> getFullyItemizedCart(final Map<String,Integer> cartProducts) {
		Map<String,Integer> itemsWithBundles = new HashMap<>(cartProducts);
		this.executeBundlingRulesForItemization(itemsWithBundles);
		return itemsWithBundles;
	}

	/**
	 * Updates the rule set with the rules to be added based on the promo codes provided
	 * @param promoCodes promo codes to be converted to pricing rules
	 */
	private void updateRulesForPromoCodes(Set<String> promoCodes) {
		for(String promoCode : promoCodes) {
			PricingRule rule = PricingRuleFactory.getRuleForCode(promoCode);
			if(rule != null) {
				this.addRule(rule);
			}
		}
	}

	/**
	 * Executes and applies all bundling pricing rules for pricing on the cart product content
	 * @param currentCart cart product content
	 */
	private void executeBundlingRulesForPricing(Map<String, Integer> currentCart) {
		for( PricingRule rule : ruleSets.get(PricingRule.RuleType.BUNDLING) ) {
			((BundlingRule)rule).updateCartForPricing(currentCart);
		}
	}
	
	/**
	 * Executes and applies all bundling pricing rules for itemization on the cart product content
	 * @param currentCart cart product content
	 */
	private void executeBundlingRulesForItemization(Map<String, Integer> currentCart) {
		for( PricingRule rule : ruleSets.get(PricingRule.RuleType.BUNDLING) ) {
			((BundlingRule)rule).updateCartForItemization(currentCart);
		}
	}

	/**
	 * Executes and applies all computation pricing rules to come up with the running cart total
	 * @param currentCart cart product content
	 * @return the running cart total
	 */
	private Double executeComputationRules(Map<String, Integer> currentCart) {
		Double total = 0.0;
		for( PricingRule rule : ruleSets.get(PricingRule.RuleType.COMPUTATION) ) {
			total = ((ComputationRule)rule).updateTotalAndCart(total, currentCart, this.catalogue);
		}
		total = new DefaultComputationRule().updateTotalAndCart(total, currentCart, this.catalogue);
		return total;
	}
	
	/**
	 * Executes and applies all finalization pricing rules on the running cart total
	 * @param total running cart total
	 * @return running cart total after applying all finalization pricing rules
	 */
	private Double executeFinalizationRules(Double total) {
		for( PricingRule rule : ruleSets.get(PricingRule.RuleType.FINALIZATION) ) {
			total = ((FinalizationRule)rule).updateTotal(total);
		}
		return total;
	}
	
	

}
