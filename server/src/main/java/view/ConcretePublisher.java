package view;

import model.Product;

public class ConcretePublisher extends Publisher {

	public void orderCompleted(Product orderedProduct, int orderedQuantity) {
		this.notifyViewers(orderedProduct, orderedQuantity);
	}
}
