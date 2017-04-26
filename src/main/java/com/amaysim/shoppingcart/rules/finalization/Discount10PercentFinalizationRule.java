package com.amaysim.shoppingcart.rules.finalization;

/**
 * This class represents represents the finalization rule:
 * "Apply a 10% discount across the board"
 */
public class Discount10PercentFinalizationRule extends FinalizationRule {

	/* (non-Javadoc)
	 * @see com.amaysim.shoppingcart.rules.finalization.FinalizationRule#updateTotal(java.lang.Double)
	 */
	@Override
	public Double updateTotal(Double currentTotal) {
		return currentTotal * 0.9;
	}

}
