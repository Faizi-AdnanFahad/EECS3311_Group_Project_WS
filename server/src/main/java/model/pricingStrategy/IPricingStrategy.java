package model.pricingStrategy;

import model.Order;

public interface IPricingStrategy {
	double calculateOrderPrice(Order order);
}
