package middleware.pricingstrategy;

import model.Order;

public class NoDiscountStrategy implements IPricingStrategy {

	public double calculateOrderPrice(Order order) {
		System.out.println("No Discount applied!");
		int regularPrice = order.getOrderedQuantity() * order.getOrderedProduct().getPrice();
		return regularPrice;
	}

}
