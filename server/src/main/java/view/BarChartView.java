package view;

import model.Product;

public class BarChartView implements IView {

	public BarChartView(Publisher sub) {
		sub.addViewers(this);
	}

	public void update(Product orderedProduct, int orderedQuantity) {
		System.out.println(
				"BAR CHART View updated with for " + orderedProduct + " with " + orderedQuantity + " quantity!");
	}

}
