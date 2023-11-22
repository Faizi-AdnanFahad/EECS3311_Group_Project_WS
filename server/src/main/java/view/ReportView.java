package view;

import model.Product;

public class ReportView implements IView {
	private Publisher publisher;

	public ReportView(Publisher sub) {
		this.publisher = sub;
	}

	public void update(Product orderedProduct, int orderedQuantity) {
		System.out
				.println("REPORT VIEW updated with for " + orderedProduct + " with " + orderedQuantity + " quantity!");
	}

}
