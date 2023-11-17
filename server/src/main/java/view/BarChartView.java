package view;

import model.Product;

public class BarChartView implements IView {

	public BarChartView(Subject sub) {
		sub.addViewers(this);
	}

	public void update(Product orderedProduct, int orderedQuantity) {
		System.out.println("Bar Chart View updated with new");
	}

}
