package view;

public class ConcreteSubject extends Subject {

	public void orderCompleted() {
		/*
		 * Here we may process the warehouse stock quantities for the product according
		 * to the order
		 */

		this.notifyViewers();
	}
}
