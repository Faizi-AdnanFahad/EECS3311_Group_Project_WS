package http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Client {
	private String baseUrl = "http://localhost:8000";
	private HttpClient client = null;
	// Create our HTTP Client
	public Client() {
		client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
	}

	public void getProducts(Integer quantity) throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/products" + "?qty=" + quantity.toString()))
				.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		System.out.println(response.body());
	}

	public void placeOrder() throws Exception {
		String requestData = "Data from client";

		BodyPublisher bp = BodyPublishers.ofString(requestData);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/order"))
				.POST(bp)
				.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		System.out.println(response.body());
	}
}
