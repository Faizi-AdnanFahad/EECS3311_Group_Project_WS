package middleware.jobs;

import javax.swing.UIManager;

import frontend.server.IServerOperation;
import frontend.web.OrderHandler;
import frontend.web.Server;
import gui.LoginGUI;
import middleware.Middleware;
import middleware.MiddlewareContext;
import util.Constants;

public class ServerOperation extends Middleware implements IServerOperation {
	public static ServerOperation instance = null;
	private ServerOperation() {
		super("Server Operation", true);
		this.activate();
	}

	public static ServerOperation getInstance() {
		if (instance == null) {
			instance = new ServerOperation();
			return instance;
		}

		return instance;
	}

	public void getProducts() {
		// TODO Auto-generated method stub

	}

	public void Init(OrderProcessorFacade opf) {
		try {
			// Setup UI
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LoginGUI loginGUI = new LoginGUI();
			loginGUI.setVisible(true);

			opf = OrderProcessorFacade.getInstance();
			// Start our HTTP server
			Server server = new Server(opf);
			server.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSystemState() {
		// TODO Auto-generated method stub
		
	}

}
