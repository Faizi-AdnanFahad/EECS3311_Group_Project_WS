package middleware.wares;

import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;

import controller.ProductController;
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
		}

		return instance;
	}

	public Map<String, Integer> getProducts() {
		ProductController pc = new ProductController();
		return pc.getAvaliableProducts();
	}

	public void Init() {
		try {
			// Setup UI
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LoginGUI loginGUI = new LoginGUI();
			loginGUI.setVisible(true);

			MiddlewareContext mCtx = MiddlewareContext.getInstance();
			OrderProcessorFacade orderProcessor = OrderProcessorFacade.getInstance();
			ServerOperation serverOperation = ServerOperation.getInstance();


			// Register our middlewares
			mCtx.register(orderProcessor);
			mCtx.register(serverOperation);

			// Start our HTTP server
			Server server = new Server();
			server.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSystemState() {
		// TODO Auto-generated method stub
		
	}

}
