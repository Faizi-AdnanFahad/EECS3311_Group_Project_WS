package view;

import gui.ServerGUI;
import model.Product;

public class ReportView implements IView {
	private Publisher publisher;

	public ReportView(Publisher sub) {
		this.publisher = sub;
	}

	public void update(Product orderedProduct, int orderedQuantity) {
		// update the GUI
		ServerGUI serverGUI = ServerGUI.getInstance();
		serverGUI.updateReportData();
	}
}
