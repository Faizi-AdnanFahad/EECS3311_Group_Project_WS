package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.HashMap;

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import utils.Constants;

// The client calls http://localhost:8000/test1?p1=10&p2=20 (e.g. from a Web browser or a Java Client program)
// and gets back as a response "Hello World! P1 was: 10 and p2 was: 20"
// If the client calls http://http://localhost:8000/test2/?p3=1000
// it gets as a response "Hello New Brave World!  p3 was: 1000"

// Note that the server can respond by sending back a Json or XML string
// which is interpreted by the client appropriately as per the logic of the client

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