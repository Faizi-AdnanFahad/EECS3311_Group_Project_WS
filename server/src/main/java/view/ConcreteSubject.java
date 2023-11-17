package view;

import model.Product;

public class ConcreteSubject extends Subject {

	public void orderCompleted(Product orderedProduct, int orderedQuantity) {
		/*
		 * Here we may modify/update the warehouse stock quantities for the product according
		 * to the order
		 */

		this.notifyViewers(orderedProduct, orderedQuantity);
	}
}
