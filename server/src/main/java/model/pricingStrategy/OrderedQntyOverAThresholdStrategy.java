package model.pricingStrategy;

import model.Order;

public class OrderedQntyOverAThresholdStrategy implements IPricingStrategy {
	private final int DICOUNT_PERCENTAGE = 5;
	
	public double calculateOrderPrice(Order order) {
		System.out.println("A 5% discount is applied since the ordered quantity exceeds x number of units.");
		int priceBeforeDiscount = order.getOrderedQuantity() * order.getOrderedProduct().getPrice();
		System.out.println("priceBeforeDiscount: " + priceBeforeDiscount);
		int discountedPrice = (int) (priceBeforeDiscount * ((100 - DICOUNT_PERCENTAGE) / 100.0));
		System.out.println("discountedPrice: " + discountedPrice);
		return discountedPrice;
	}

}
