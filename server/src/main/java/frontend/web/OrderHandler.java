package frontend.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.ProductController;
import middleware.OrderQueue;
import middleware.jobs.OrderProcessorFacade;
import model.Order;
import model.Product;

public class OrderHandler implements HttpHandler {

	private OrderProcessorFacade processor = null;

	public void setProcessor(OrderProcessorFacade opf) {
		this.processor = opf;
	}
	
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("Order recieved.");

		// Construct our response to be sent
		String response = "Order placed Succesfully";

		// Add the order to the OrderQueue
		
		// First get our input stream
		InputStream in = exchange.getRequestBody();
		
		// Read all the bytes of data as UTF-8 character encoding
		String data = new String(in.readAllBytes(), Charset.forName("UTF8"));
		
		// Print out all our data
		System.out.println(data);
		
		
		// hard coded for now - in deliverable 3, this data would be coming from client.
		String orderedProductName = data;
		
		Product orderedProduct = searchProductByName(orderedProductName);		
		
		// Create our order
		Order order = new Order(orderedProduct, 33);

		// Fill our order object with data
		
		// Add to Order Queue
		processor.add(order);
		

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
