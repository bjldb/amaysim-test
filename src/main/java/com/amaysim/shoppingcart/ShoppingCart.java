package com.amaysim.shoppingcart;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.amaysim.shoppingcart.catalogue.CatalogueProduct;
import com.amaysim.shoppingcart.rules.PricingRules;

/**
 * This class represents a generic shopping cart with customizable pricing rules
 */
public class ShoppingCart {
	
	private Map<CatalogueProduct,Integer> items;
	
	private Set<String> promoCodes;
	private PricingRules pricingRules;
	
	/**
	 * Constructor
	 * @param pricingRules pricing rules for this shopping cart instance
	 */
	public ShoppingCart(PricingRules pricingRules) {
		this.items = new EnumMap<CatalogueProduct,Integer>(CatalogueProduct.class);
		this.promoCodes = new HashSet<>();
		
		this.pricingRules = pricingRules;
	}
	
	/**
	 * Adds a product from the catalogue into the shopping cart
	 * @param item product to add to cart
	 */
	public void add(CatalogueProduct item) {
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
	public void add(CatalogueProduct item, String promoCode) {	
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
	public Map<CatalogueProduct,Integer> items() {
		return pricingRules.getFullyItemizedCart(this.items);
	}
	
	/**
	 * Returns a string fully describing the shopping cart
	 */
	@Override
	public String toString() {
		
		Map<CatalogueProduct,Integer> addedItems = this.items;
		Map<CatalogueProduct,Integer> cartItems = this.items();
		
		StringBuilder cartStringBuilder = new StringBuilder("==============================\n");
		cartStringBuilder.append("ITEMS ADDED:\n");
		cartStringBuilder.append("==============================\n");
		
		// Added Items
		for(CatalogueProduct product : addedItems.keySet()) {
			cartStringBuilder.append(addedItems.get(product)).append(" x ");
			cartStringBuilder.append(product.productName()).append("\n");
		}
		
		// Promo Codes
		for(String promoCode : promoCodes) {
			cartStringBuilder.append("'").append(promoCode).append("' Promo Applied\n");
		}
		
		cartStringBuilder.append("==============================\n");
		cartStringBuilder.append("RESULTING CART ITEMS:\n");
		cartStringBuilder.append("==============================\n");
		
		// Final Cart Content
		for(CatalogueProduct product : cartItems.keySet()) {
			cartStringBuilder.append(cartItems.get(product)).append(" x ");
			cartStringBuilder.append(product.productName()).append("\n");
		}
		
		// Cart Total  
		cartStringBuilder.append("==============================\n");
		cartStringBuilder.append(String.format("CART TOTAL: $%.2f\n", this.total()));
		cartStringBuilder.append("==============================\n");
		
		return cartStringBuilder.toString();
	}

}
