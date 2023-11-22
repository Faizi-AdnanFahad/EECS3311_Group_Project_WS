package frontend;

import javax.swing.UIManager;
import frontend.web.Server;
import gui.LoginGUI;
import middleware.MiddlewareContext;
import middleware.jobs.*;

public class Main {

	public static void main(String[] args) {

		// Create our middleware Context
		MiddlewareContext mCtx = new MiddlewareContext();

		OrderProcessorFacade op = OrderProcessorFacade.getInstance();
		// Register our Order Processor middleware
		mCtx.register(op);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LoginGUI loginGUI = new LoginGUI();
			loginGUI.setVisible(true);

			Server http = new Server(op);
			http.start();

			// Process orders if they exist
			while (op.isActive())
				op.process();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
