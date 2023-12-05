package middleware.pricingstrategy;

import model.Order;

public class NoDiscountStrategy implements IPricingStrategy {

	public double calculateOrderPrice(Order order) {
		int regularPrice = order.getOrderedQuantity() * order.getOrderedProduct().getPrice();
		return regularPrice;
	}

}
