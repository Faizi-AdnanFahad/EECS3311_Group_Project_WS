package model.pricingStrategy;

import model.Order;

public class OrderedTotalOverAThresholdStrategy implements IPricingStrategy {
	private final int UNIT_DICOUNT_PERCENTAGE = 5;
	private final int ADDITIONAL_DISCOUNT_PERCENTAGE = 5;

	public double calculateOrderPrice(Order order) {
		System.out.println(
				"A 5% discount + an additional 5% disocunt is applied since the ordered quantity exceeds x number of units and the total ordered price exceeds $1000.");
		int priceBeforeDiscount = order.getOrderedQuantity() * order.getOrderedProduct().getPrice();
		int discountPrice1 = (int) (priceBeforeDiscount * ((100 - UNIT_DICOUNT_PERCENTAGE) / 100.0));
		int discountPrice2 = (int) (discountPrice1 * ((100 - ADDITIONAL_DISCOUNT_PERCENTAGE) / 100.0));
		return discountPrice2;
	}

}
