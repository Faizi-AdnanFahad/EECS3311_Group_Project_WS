package controller;

import view.BarChartView;
import view.ConcreteSubject;
import view.IView;
import view.ReportView;
import view.Subject;

public class OrderController {
	
	public void orderCompleted() {
		// update the backend db and update all the viewers now
		
		ConcreteSubject concreteSubject = new ConcreteSubject();
		IView barChartView = new BarChartView(concreteSubject);
		IView reportView = new ReportView(concreteSubject);
		
		concreteSubject.orderCompleted();
	}

}
