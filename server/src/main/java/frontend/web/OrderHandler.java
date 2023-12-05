package frontend.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.ProductController;
import middleware.wares.OrderProcessorFacade;
import model.Order;
import model.Product;
import util.Constants;

public class OrderHandler implements HttpHandler {

	public void handle(HttpExchange exchange) throws IOException {

		try {
			// First get our input stream
			InputStream in = exchange.getRequestBody();

			// Read all the bytes of data as UTF-8 character encoding
			String data = new String(in.readAllBytes(), Charset.forName("UTF8"));

			String[] parsedData = data.split(",");

			String product = parsedData[0];
			String qty = parsedData[1];

			Product orderedProduct = searchProductByName(product);

			// Create our order
			Order order = new Order(orderedProduct, Integer.parseInt(qty));

			// Add to Order Queue
			OrderProcessorFacade processor = ((OrderProcessorFacade) Constants.MIDDLEWARE_MAP.get("/order"));
			processor.add(order);

			processor.setLatch(new CountDownLatch(1));

			// Close our input stream
			in.close();

			// Get our output stream
			processor.getLatch().await();

			String message = processor.getMessageQueue().take();

			// Send the response headers
			exchange.sendResponseHeaders(200, message.length());

			OutputStream out = exchange.getResponseBody();

			// Send back the response body with data from our response
			out.write(message.getBytes());

			// Close our output stream
			out.close();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Product searchProductByName(String productName) {
		// get the list of all products
		ProductController productController = new ProductController();
		List<Product> products = productController.getAllProduct();

		for (Product product : products) {
			if (product.getName().equals(productName)) {
				return product;
			}
		}
		return null;
	}
}
