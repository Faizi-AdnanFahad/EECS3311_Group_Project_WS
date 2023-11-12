package server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import utils.Constants;

public class Server {
	final int port = 8000;
	
	public void startServer() throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

		for(int i = 0; i < Constants.ROUTES.length; ++i) {
			server.createContext(Constants.ROUTES[i], Constants.HANDLERS[i]);
		}

		server.setExecutor(Executors.newCachedThreadPool());
		server.start();

		System.out.printf("Started server on http://localhost:%d\n", port);
		System.out.printf("Avaliable routes are: \n");
		for(String route : Constants.ROUTES) System.out.println(route);
	}

}