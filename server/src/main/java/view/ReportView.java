package view;

import model.Product;

public class ReportView implements IView {

	public ReportView(Publisher sub) {
		sub.addViewers(this);
	}

	public void update(Product orderedProduct, int orderedQuantity) {
		System.out.println("Report View updated!");
	}

}
