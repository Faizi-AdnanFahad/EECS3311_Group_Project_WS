package middleware.pricingstrategy;

import model.Order;

public interface IPricingStrategy {
	double calculateOrderPrice(Order order);
}
