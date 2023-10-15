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

// The client calls http://localhost:8000/test1?p1=10&p2=20 (e.g. from a Web browser or a Java Client program)
// and gets back as a response "Hello World! P1 was: 10 and p2 was: 20"
// If the client calls http://http://localhost:8000/test2/?p3=1000
// it gets as a response "Hello New Brave World!  p3 was: 1000"

// Note that the server can respond by sending back a Json or XML string
// which is interpreted by the client appropriately as per the logic of the client

public class Server {
	public void startServer() throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/test1", new MyHandler1());
		server.createContext("/test2", new MyHandler2());
		server.setExecutor(Executors.newCachedThreadPool());
		server.start();
		System.out.println("Started server");
	}

	static class MyHandler1 implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			System.out.println(exchange.toString());
			System.out.println("test 1");
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
			String response = "Hello World! " + "P1 was: " + parms.get("p1") + " and p2 was: " + parms.get("p2");
//			String response = "Hello, We are in test 1";
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			try {
				wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response = " FOO";
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os2 = exchange.getResponseBody();
			os2 = exchange.getResponseBody();
			os2.write(response.getBytes());
			os.close();
			os2.close();
		}
	}
	
	
	static class MyHandler2 implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			System.out.println("test 2");
//			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
			String response = "Hello, We are in test 2";
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	public static Map<String, String> queryToMap(String query){

	    Map<String, String> result = new HashMap<String, String>();
	    for (String param : query.split("&")) {
	        String pair[] = param.split("=");
	        if (pair.length>1) {
	            result.put(pair[0], pair[1]);
	        }else{
	            result.put(pair[0], "");
	        }
	    }
	    return result;
	  }
}