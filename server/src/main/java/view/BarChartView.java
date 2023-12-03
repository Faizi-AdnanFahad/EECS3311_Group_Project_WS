package view;

import javax.swing.JFrame;

import gui.ServerGUI;
import model.Product;

public class BarChartView implements IView {
	private Publisher publisher;

	public BarChartView(Publisher sub) {
		this.publisher = sub;
	}

	public void update(Product orderedProduct, int orderedQuantity) {
		System.out.println(
				"BAR CHART View updated with for " + orderedProduct.getName() + " with " + orderedQuantity + " quantity!");

		// update the GUI
		ServerGUI serverGUI = ServerGUI.getInstance();
		serverGUI.updateBar();
	}

}
