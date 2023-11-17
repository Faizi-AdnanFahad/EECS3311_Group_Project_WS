package web;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class OrderHandler implements HttpHandler {

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
