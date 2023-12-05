package frontend.web;

import java.net.InetSocketAddress;

import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import util.Constants;

public class Server {
	private HttpServer server = null;

	public Server() {

		try {
			server = HttpServer.create(new InetSocketAddress(Constants.PORT), 0);

			for (String route : Constants.ROUTE_MAP.keySet()) {
				server.createContext(route, Constants.ROUTE_MAP.get(route));
			}

			server.setExecutor(Executors.newCachedThreadPool());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Start our server
	public void start() throws Exception {
		server.start();

		System.out.printf("Started server on http://localhost:%d\n", Constants.PORT);
		System.out.printf("Avaliable routes are: \n");

		for (String route : Constants.ROUTE_MAP.keySet())
			System.out.println(route);
	}

}