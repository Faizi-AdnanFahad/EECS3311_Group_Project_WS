package http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Client {
	private String baseUrl = "http://localhost:8000";
	private HttpClient client = null;

	public Client() {
		client = HttpClient.newHttpClient();
		try {
			String products = this.get(baseUrl + "/products");
			System.out.println(products);

		} catch (Exception e) {

		}
	}

	// Blocking
	public String get(String uri) throws Exception {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		return response.body();
	}

}
