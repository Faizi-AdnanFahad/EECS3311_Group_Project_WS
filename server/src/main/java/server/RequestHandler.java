package server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import utils.Utility;

public class RequestHandler {
	public RequestHandler() { } 

	public static class OrderHandler implements HttpHandler {

		public void handle(HttpExchange exchange) throws IOException {
			System.out.println(exchange.toString());
			System.out.println("test 1");
			Map<String, String> parms = Utility.queryToMap(exchange.getRequestURI().getQuery());
//			String response = "Hello World! " + "P1 was: " + parms.get("p1") + " and p2 was: " + parms.get("p2");
			String response = "Hello, We are in test 1";
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

	public static class ProductHandler implements HttpHandler {

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
}
