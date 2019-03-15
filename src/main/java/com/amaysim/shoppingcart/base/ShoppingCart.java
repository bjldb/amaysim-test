package com.amaysim.shoppingcart.base;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.amaysim.shoppingcart.base.catalogue.Catalogue;
import com.amaysim.shoppingcart.base.catalogue.CatalogueProduct;
import com.amaysim.shoppingcart.base.rules.PricingRules;

/**
 * This class represents a generic shopping cart with customizable pricing rules
 */
public class ShoppingCart {
	
	private Map<String,Integer> items;
	
	private Set<String> promoCodes;
	private PricingRules pricingRules;
	private Catalogue catalogue;

	/**
	 * Constructor
	 * @param pricingRules pricing rules for this shopping cart instance
	 */
	public ShoppingCart(PricingRules pricingRules) {
		this.items = new HashMap<String,Integer>();
		this.promoCodes = new HashSet<>();
		
		this.pricingRules = pricingRules;
		this.catalogue = pricingRules.getCatalogue();
	}
	
	/**
	 * Adds a product from the catalogue into the shopping cart
	 * @param item product to add to cart
	 */
	public void add(String item) {
		if(items.containsKey(item)) {
			items.put(item, items.get(item) + 1);
		}
		else {
			items.put(item, 1);
		}
	}
	
	/**
	 * Adds a product from the catalogue into the shopping cart with a promo code
	 * @param item item product to add to cart
	 * @param promoCode promo code to add
	 */
	public void add(String item, String promoCode) {	
		this.add(item);
		promoCodes.add(promoCode);
	}
	
	/**
	 * Computes the total equivalent amount payable for all the items in the shopping cart
	 * @return the total equivalent amount payable for all the items in the shopping cart
	 */
	public Double total() {
		return pricingRules.computeTotalPrice(this.items,this.promoCodes);
	}
	
	/**
	 * Generates a map of products and equivalent quantity after applying all pricing rules and promo codes
	 * @return the generated map of products and quantity
	 */
	public Map<String,Integer> items() {
		return pricingRules.getFullyItemizedCart(this.items);
	}
	
	/**
	 * Returns a string fully describing the shopping cart
	 */
	@Override
	public String toString() {
		
		Map<String,Integer> addedItems = this.items;
		Map<String,Integer> cartItems = this.items();
		
		StringBuilder cartStringBuilder = new StringBuilder("==============================\n");
		cartStringBuilder.append("ITEMS ADDED:\n");
		cartStringBuilder.append("==============================\n");
		
		// Added Items
		for(String productName : addedItems.keySet()) {
			CatalogueProduct product = this.catalogue.getProduct(productName);
			cartStringBuilder.append(addedItems.get(productName)).append(" x ");
			cartStringBuilder.append(product.getProductName()).append("\n");
		}
		
		// Promo Codes
		for(String promoCode : promoCodes) {
			cartStringBuilder.append("'").append(promoCode).append("' Promo Applied\n");
		}
		
		cartStringBuilder.append("==============================\n");
		cartStringBuilder.append("RESULTING CART ITEMS:\n");
		cartStringBuilder.append("==============================\n");
		
		// Final Cart Content
		for(String productName : cartItems.keySet()) {
			CatalogueProduct product = this.catalogue.getProduct(productName);
			cartStringBuilder.append(cartItems.get(productName)).append(" x ");
			cartStringBuilder.append(product.getProductName()).append("\n");
		}
		
		// Cart Total  
		cartStringBuilder.append("==============================\n");
		cartStringBuilder.append(String.format("CART TOTAL: $%.2f\n", this.total()));
		cartStringBuilder.append("==============================\n");
		
		return cartStringBuilder.toString();
	}

}
