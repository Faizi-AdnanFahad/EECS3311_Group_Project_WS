package server;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;
import controller.OrderController;
import database.AdminDAO;
import database.ProductDAO;
import gui.LoginGUI;
import gui.ServerGUI;
import model.Product;
import model.User;
import middleware.MiddlewareContext;
import middleware.OrderProcessor;
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
		
		
		AdminDAO a = new AdminDAO();
		List<User> u = a.retriveUsernameAndPassword();

		for(User user : u) {
			System.out.println("The username is:" + user.getUsername() +", the password is :" + user.getPassword());
		}
		
		ProductDAO product = new ProductDAO();
		List<Product> prod = product.retriveProductDetails();
		
		if(prod !=null) {
		
			for(Product p : prod) {
				System.out.println("The Id is: " + p.getId() + ", the Name is " + p.getName() + ", the price is " + p.getPrice() + ", the stockQuantity is " + p.getStockQuantity() + " .");
			}
		}
		
		
//		System.out.println("----------------Observer Pattern----------------");
//		OrderController orderController = new OrderController();
//		orderController.orderCompleted();
//		System.out.println("------------------------------------------------");
		
		// Create our middleware Context
		MiddlewareContext mCtx = new MiddlewareContext();
		OrderProcessor op = OrderProcessor.getInstance();
		
		// Register our Order Processor middleware
		mCtx.register(op);

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
