package frontend.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.ProductController;
import model.Product;

public class ProductHandler implements HttpHandler {

	public void handle(HttpExchange exchange) throws IOException {

		// First get our input stream
		InputStream in = exchange.getRequestBody();
		String query = exchange.getRequestURI().getQuery();

		// Read all the bytes of data as UTF-8 character encoding
		String data = new String(in.readAllBytes(), Charset.forName("UTF8"));

		// Close our input stream
		in.close();

		// Construct our response to be sent
		String response = fetchResponseWithProductList();

		// Send the response headers
		exchange.sendResponseHeaders(200, response.length());

		// Get our output stream
		OutputStream out = exchange.getResponseBody();

		// Send back the response body with data from our response
		out.write(response.getBytes());

		// Close our output stream
		out.close();
	}

	/*
	 * Helper method to build a response with a list of products available in
	 * the product database.
	 */
	private String fetchResponseWithProductList() {
		StringBuilder sb = new StringBuilder();

		ProductController productController = new ProductController();
		List<Product> products = productController.getAllProduct();

		// populate the string builder with products in the database
		for (Product product : products) {
			sb.append(product.getId() + "-" + product.getName());
			sb.append("\n");
		}

		return sb.toString();
	}
}
