package server;

import javax.swing.JFrame;
import javax.swing.UIManager;
import controller.OrderController;
import gui.LoginGUI;
import gui.ServerGUI;
import web.Server;

public class Main {

	public static void main(String[] args) {

		JFrame frame = ServerGUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
		Server http = new Server();

		// for testing observer and update of viewer purposes
		System.out.println("----------------Observer Pattern----------------");
		OrderController orderController = new OrderController();
		orderController.orderCompleted();
		System.out.println("------------------------------------------------");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LoginGUI loginGUI = new LoginGUI();
			loginGUI.setVisible(true);
			http.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
