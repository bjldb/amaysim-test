package com.amaysim.shoppingcart;

import org.junit.Assert;
import org.junit.Test;

import com.amaysim.shoppingcart.catalogue.CatalogueProduct;
import com.amaysim.shoppingcart.rules.PricingRuleFactory;
import com.amaysim.shoppingcart.rules.PricingRules;

public class ShoppingCartTest {

	private PricingRules getPricingRules() {
		PricingRules rules = new PricingRules();
		rules.addRule(PricingRuleFactory.getRuleForCode("Unli2GBBundle"));
		rules.addRule(PricingRuleFactory.getRuleForCode("Unli1GBDeal"));
		rules.addRule(PricingRuleFactory.getRuleForCode("Unli5GBBulk"));
		return rules;
	}
	
	@Test
	public void Test_Scenario_1_ThreeForTwoForUnli1GB() {
		
		ShoppingCart cart = new ShoppingCart(this.getPricingRules());
		
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ULT_LARGE);
		
		System.out.println("Scenario 1: A 3 for 2 deal on Unlimited 1GB Sims. So for example, if you buy 3 Unlimited 1GB Sims, you will pay the price of 2 only for the first month.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 94.70, cart.total(), 2);
		
		Assert.assertEquals((Integer) 3, cart.items().get(CatalogueProduct.ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ULT_LARGE));
		
	}
	
	@Test
	public void Test_Scenario_2_PriceDropForUnli5GBForPurchaseMoreThan3() {
		
		ShoppingCart cart = new ShoppingCart(this.getPricingRules());
		
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ULT_LARGE);
		cart.add(CatalogueProduct.ULT_LARGE);
		cart.add(CatalogueProduct.ULT_LARGE);
		cart.add(CatalogueProduct.ULT_LARGE);
		
		System.out.println("Scenario 2: The Unlimited 5GB Sim will have a bulk discount applied; whereby the price will drop to $39.90 each for the first month, if the customer buys more than 3.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 209.40, cart.total(), 2);
		
		Assert.assertEquals((Integer) 2, cart.items().get(CatalogueProduct.ULT_SMALL));
		Assert.assertEquals((Integer) 4, cart.items().get(CatalogueProduct.ULT_LARGE));
		
	}
	
	@Test
	public void Test_Scenario_3_Unordered1GBDataPackAppearOnCartAsFree() {
		
		ShoppingCart cart = new ShoppingCart(this.getPricingRules());
		
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ULT_MEDIUM);
		cart.add(CatalogueProduct.ULT_MEDIUM);
		
		System.out.println("Scenario 3: We will bundle in a free 1 GB Data-pack free-of-charge with every Unlimited 2GB sold.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 84.70, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ULT_SMALL));
		Assert.assertEquals((Integer) 2, cart.items().get(CatalogueProduct.ULT_MEDIUM));
		Assert.assertEquals((Integer) 2, cart.items().get(CatalogueProduct.ONE_GB));
		
	}
	
	@Test
	public void Test_Scenario_4_PromoApplied() {
		
		ShoppingCart cart = new ShoppingCart(this.getPricingRules());
		
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ONE_GB,"I<3AMAYSIM");
		
		System.out.println("Scenario 4: Adding the promo code 'I<3AMAYSIM' will apply a 10% discount across the board.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 31.32, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ONE_GB));
		
	}
	
	@Test
	public void Test_Scenario_5_Paired1GBDataPackFree() {
		
		ShoppingCart cart = new ShoppingCart(this.getPricingRules());
		
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ULT_MEDIUM);
		cart.add(CatalogueProduct.ONE_GB); // free
		
		System.out.println("Scenario 5: All 1 GB Data-pack paired with any ordered Unlimited 2GB are free.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 54.8, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ULT_MEDIUM));
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ONE_GB));
		
	}
	
	@Test
	public void Test_Scenario_6_PayOnlyUnpaired1GBDataPack() {
		
		ShoppingCart cart = new ShoppingCart(this.getPricingRules());
		
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ULT_MEDIUM);
		cart.add(CatalogueProduct.ONE_GB); // free
		cart.add(CatalogueProduct.ONE_GB); // unpaired
		
		System.out.println("Scenario 6: Pay only for 1 GB Data-pack not paired with any ordered Unlimited 2GB.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 64.7, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ULT_MEDIUM));
		Assert.assertEquals((Integer) 2, cart.items().get(CatalogueProduct.ONE_GB));
		
	}
	
	@Test
	public void Test_Scenario_7_PromoNotExisting() {
		
		ShoppingCart cart = new ShoppingCart(this.getPricingRules());
		
		cart.add(CatalogueProduct.ULT_SMALL);
		cart.add(CatalogueProduct.ONE_GB,"I<3AMAYSING"); // promo code not existing
		
		System.out.println("Scenario 7: Promo Code not existing.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 34.8, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(CatalogueProduct.ONE_GB));
		
	}



}
