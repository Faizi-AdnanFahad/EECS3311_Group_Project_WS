package middleware.pricingstrategy;

import model.Order;

public class OrderedQntyOverAThresholdStrategy implements IPricingStrategy {
	private final int DICOUNT_PERCENTAGE = 5;
	
	public double calculateOrderPrice(Order order) {
		int priceBeforeDiscount = order.getOrderedQuantity() * order.getOrderedProduct().getPrice();
		int discountedPrice = (int) (priceBeforeDiscount * ((100 - DICOUNT_PERCENTAGE) / 100.0));
		return discountedPrice;
	}

}
