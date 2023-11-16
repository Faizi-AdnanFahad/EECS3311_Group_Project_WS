package controller;

import model.Order;
import model.Product;
import view.BarChartView;
import view.ConcreteSubject;
import view.IView;
import view.ReportView;

public class OrderController {

	/*
	 * This method, updates all viewers using the observer pattern once an order is
	 * complete.
	 */
	public void orderCompleted() {
		Product p = new Product();
		int quantity = 2;
		Order order = new Order(p, quantity);

		ConcreteSubject concreteSubject = new ConcreteSubject();
		// add viewers as observers
		IView barChartView = new BarChartView(concreteSubject);
		IView reportView = new ReportView(concreteSubject);

		order.updateView(concreteSubject);

	}

}
