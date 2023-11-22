package view;

import model.Product;

public class BarChartView implements IView {
	private Publisher publisher;

	public BarChartView(Publisher sub) {
		this.publisher = sub;
	}

	public void update(Product orderedProduct, int orderedQuantity) {
		System.out.println(
				"BAR CHART View updated with for " + orderedProduct + " with " + orderedQuantity + " quantity!");
	}

}
