package web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ProductHandler implements HttpHandler {

	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("Getting products...");

		// Construct our response to be sent
		String response = "Products recieved successfully";

		
		// First get our input stream
		InputStream in = exchange.getRequestBody();
		String query = exchange.getRequestURI().getQuery();

		System.out.println(query);
		
		// Read all the bytes of data as UTF-8 character encoding
		String data = new String(in.readAllBytes(), Charset.forName("UTF8"));
		
		// Print out all our data
		System.out.println(data);

		// Close our input stream
		in.close();
		
		// Send the response headers
		exchange.sendResponseHeaders(200, response.length());

		// Get our output stream
		OutputStream out = exchange.getResponseBody();

		// Send back the response body with data from our response
		out.write(response.getBytes());

		// Close our output stream
		out.close();
	}
}