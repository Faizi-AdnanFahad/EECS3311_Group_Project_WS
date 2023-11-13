package server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import utils.Utility;

public class RequestHandler {
	public RequestHandler() {
	}

	public static class OrderHandler implements HttpHandler {

		public void handle(HttpExchange exchange) throws IOException {
			System.out.println(exchange.toString());
			System.out.println("test 1");
			System.out.println("Ali Umar");

			String response = "This should return handle ordering";

			System.out.println("Ali" + response);
			exchange.sendResponseHeaders(200, response.length());

			OutputStream os = exchange.getResponseBody();

			os.write(response.getBytes());
			os.close();
		}
	}

	public static class ProductHandler implements HttpHandler {

		public void handle(HttpExchange exchange) throws IOException {
			System.out.println(exchange.toString());
			System.out.println("test 2");
			System.out.println("Ali Umar");

			String response = "This should return a list products";

			System.out.println("Ali" + response);
			exchange.sendResponseHeaders(200, response.length());

			OutputStream os = exchange.getResponseBody();

			os.write(response.getBytes());
			os.close();
		}
	}
}
