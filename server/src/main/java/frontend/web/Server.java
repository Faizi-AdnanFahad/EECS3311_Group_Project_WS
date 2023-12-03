package frontend.web;

import java.net.InetSocketAddress;

import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import middleware.Middleware;
import middleware.MiddlewareContext;
import middleware.jobs.OrderProcessorFacade;
import util.Constants;

public class Server {
	private HttpServer server = null;
	private OrderProcessorFacade orderProcessor = null;

	// Init HttpServer
	public Server(OrderProcessorFacade opf) {
		orderProcessor = opf;

		try {
			server = HttpServer.create(new InetSocketAddress(Constants.PORT), 0);

			HttpHandler oh =  Constants.HANDLERS[0];

			((OrderHandler) oh).setProcessor(opf);

			for (int i = 0; i < Constants.ROUTES.length; ++i) {
				server.createContext(Constants.ROUTES[i], Constants.HANDLERS[i]);
			}

			server.setExecutor(Executors.newCachedThreadPool());

		} catch (Exception e) {
			System.out.println("Error occured in Server: ");
			e.printStackTrace();
		}
	}
	

	// Start our server
	public void start() throws Exception {
		server.start();

		System.out.printf("Started server on http://localhost:%d\n", Constants.PORT);
		System.out.printf("Avaliable routes are: \n");

		for (String route : Constants.ROUTES)
			System.out.println(route);
	}

}