package com.amaysim.shoppingcart;

import org.junit.Assert;
import org.junit.Test;

import com.amaysim.shoppingcart.base.ShoppingCart;
import com.amaysim.shoppingcart.base.catalogue.ICatalogueProvider;
import com.amaysim.shoppingcart.base.rules.PricingRuleFactory;
import com.amaysim.shoppingcart.base.rules.PricingRules;
import com.amaysim.shoppingcart.implem.catalogue.CsvCatalogueProvider;

public class ShoppingCartTest {

	private final String ULT_SMALL = "ult_small";
	private final String ULT_MEDIUM = "ult_medium";
	private final String ULT_LARGE= "ult_large";
	private final String ONE_GB = "1gb";
	
	private final ICatalogueProvider catalogueProvider = new CsvCatalogueProvider("catalogue.csv");

	private PricingRules getSpecifiedPricingRules() throws Exception {
		PricingRules rules = new PricingRules(catalogueProvider);
		rules.addRule(PricingRuleFactory.getRuleForCode("Unli2GBBundle"));
		rules.addRule(PricingRuleFactory.getRuleForCode("Unli1GBDeal"));
		rules.addRule(PricingRuleFactory.getRuleForCode("Unli5GBBulk"));
		return rules;
	}
	
	private PricingRules getEmptyPricingRules() throws Exception {
		PricingRules rules = new PricingRules(catalogueProvider);
		return rules;
	}
	
	@Test
	public void Test_Scenario_1_ThreeForTwoForUnli1GB() throws Exception {
		ShoppingCart cart = new ShoppingCart(this.getSpecifiedPricingRules());
		
		cart.add(ULT_SMALL);
		cart.add(ULT_SMALL);
		cart.add(ULT_SMALL);
		cart.add(ULT_LARGE);
		
		System.out.println("Scenario 1: A 3 for 2 deal on Unlimited 1GB Sims. So for example, if you buy 3 Unlimited 1GB Sims, you will pay the price of 2 only for the first month.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 94.70, cart.total(), 2);
		
		Assert.assertEquals((Integer) 3, cart.items().get(ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(ULT_LARGE));
	}
	
	@Test
	public void Test_Scenario_2_PriceDropForUnli5GBForPurchaseMoreThan3() throws Exception {
		ShoppingCart cart = new ShoppingCart(this.getSpecifiedPricingRules());
		
		cart.add(ULT_SMALL);
		cart.add(ULT_SMALL);
		cart.add(ULT_LARGE);
		cart.add(ULT_LARGE);
		cart.add(ULT_LARGE);
		cart.add(ULT_LARGE);
		
		System.out.println("Scenario 2: The Unlimited 5GB Sim will have a bulk discount applied; whereby the price will drop to $39.90 each for the first month, if the customer buys more than 3.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 209.40, cart.total(), 2);
		
		Assert.assertEquals((Integer) 2, cart.items().get(ULT_SMALL));
		Assert.assertEquals((Integer) 4, cart.items().get(ULT_LARGE));
	}
	
	@Test
	public void Test_Scenario_3_Unordered1GBDataPackAppearOnCartAsFree() throws Exception {
		ShoppingCart cart = new ShoppingCart(this.getSpecifiedPricingRules());
		
		cart.add(ULT_SMALL);
		cart.add(ULT_MEDIUM);
		cart.add(ULT_MEDIUM);
		
		System.out.println("Scenario 3: We will bundle in a free 1 GB Data-pack free-of-charge with every Unlimited 2GB sold.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 84.70, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(ULT_SMALL));
		Assert.assertEquals((Integer) 2, cart.items().get(ULT_MEDIUM));
		Assert.assertEquals((Integer) 2, cart.items().get(ONE_GB));
	}
	
	@Test
	public void Test_Scenario_4_PromoApplied() throws Exception {
		ShoppingCart cart = new ShoppingCart(this.getSpecifiedPricingRules());
		
		cart.add(ULT_SMALL);
		cart.add(ONE_GB,"I<3AMAYSIM");
		
		System.out.println("Scenario 4: Adding the promo code 'I<3AMAYSIM' will apply a 10% discount across the board.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 31.32, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(ONE_GB));
	}
	
	@Test
	public void Test_Scenario_5_Paired1GBDataPackFree() throws Exception {	
		ShoppingCart cart = new ShoppingCart(this.getSpecifiedPricingRules());
		
		cart.add(ULT_SMALL);
		cart.add(ULT_MEDIUM);
		cart.add(ONE_GB); // free
		
		System.out.println("Scenario 5: All 1 GB Data-pack paired with any ordered Unlimited 2GB are free.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 54.8, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(ULT_MEDIUM));
		Assert.assertEquals((Integer) 1, cart.items().get(ONE_GB));
	}
	
	@Test
	public void Test_Scenario_6_PayOnlyUnpaired1GBDataPack() throws Exception {
		ShoppingCart cart = new ShoppingCart(this.getSpecifiedPricingRules());
		
		cart.add(ULT_SMALL);
		cart.add(ULT_MEDIUM);
		cart.add(ONE_GB); // free
		cart.add(ONE_GB); // unpaired
		
		System.out.println("Scenario 6: Pay only for 1 GB Data-pack not paired with any ordered Unlimited 2GB.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 64.7, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(ULT_MEDIUM));
		Assert.assertEquals((Integer) 2, cart.items().get(ONE_GB));
	}
	
	@Test
	public void Test_Scenario_7_PromoNotExisting() throws Exception {
		ShoppingCart cart = new ShoppingCart(this.getSpecifiedPricingRules());
		
		cart.add(ULT_SMALL);
		cart.add(ONE_GB,"I<3AMAYSING"); // promo code not existing, no discount
		
		System.out.println("Scenario 7: Promo Code not existing.");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 34.8, cart.total(), 2);
		
		Assert.assertEquals((Integer) 1, cart.items().get(ULT_SMALL));
		Assert.assertEquals((Integer) 1, cart.items().get(ONE_GB));
	}

	@Test
	public void Test_Scenario_8_NoOffersPromotions() throws Exception {	
		ShoppingCart cart = new ShoppingCart(this.getEmptyPricingRules());
		
		cart.add(ULT_SMALL);
		cart.add(ULT_SMALL);
		cart.add(ULT_SMALL); // Not free for 3 for 2, no promo
		cart.add(ULT_MEDIUM);
		cart.add(ULT_MEDIUM);
		cart.add(ULT_LARGE); // Not 39.90, no promo
		cart.add(ULT_LARGE); // Not 39.90, no promo
		cart.add(ULT_LARGE); // Not 39.90, no promo
		cart.add(ULT_LARGE); // Not 39.90, no promo
		cart.add(ONE_GB); // Not free, no promo
		cart.add(ONE_GB); // Not free, no promo
		
		System.out.println("Scenario 8: No Offers or Promotions");
		System.out.println(cart);
		
		// Default Pricing Rule in effect
		Assert.assertEquals((Double) 333.9, cart.total(), 2);
		
		Assert.assertEquals((Integer) 3, cart.items().get(ULT_SMALL));
		Assert.assertEquals((Integer) 2, cart.items().get(ULT_MEDIUM));
		Assert.assertEquals((Integer) 4, cart.items().get(ULT_LARGE));
		Assert.assertEquals((Integer) 2, cart.items().get(ONE_GB));
	}
	
	@Test
	public void Test_Scenario_9_EmptyCart() throws Exception {
		ShoppingCart cart = new ShoppingCart(this.getEmptyPricingRules());
		
		System.out.println("Scenario 9: Empty Cart");
		System.out.println(cart);
		
		Assert.assertEquals((Double) 0.0, cart.total(), 2);
		
		Assert.assertTrue(cart.items().isEmpty());
	}

}
