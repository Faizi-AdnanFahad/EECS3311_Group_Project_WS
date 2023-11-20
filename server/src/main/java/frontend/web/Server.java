package frontend.web;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import middleware.MiddlewareContext;
import middleware.jobs.OrderProcessorFacade;
import util.Constants;
import frontend.web.OrderHandler;
public class Server {
	private final int port = 8000;
	private HttpServer server = null;
	private MiddlewareContext middlewareHandle = null;

	// Bootstrap the HttpServer 
	public Server(OrderProcessorFacade opf) {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);

			OrderHandler oh = (OrderHandler) Constants.HANDLERS[0];
			oh.setProcessor(opf);

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

		System.out.printf("Started server on http://localhost:%d\n", port);
		System.out.printf("Avaliable routes are: \n");

		for (String route : Constants.ROUTES) System.out.println(route);
	}

}