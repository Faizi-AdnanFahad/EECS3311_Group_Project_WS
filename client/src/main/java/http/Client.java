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
	public Client() {
		client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
		try {
			String products = this.post(baseUrl + "/order");
			System.out.println();
			System.out.println(products);

		} catch (Exception e) {
			System.out.println("Error occured" + e.getMessage());
		}
	}

	// Blocking
	public String get(String uri) throws Exception {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		return response.body();
	}

	// Blocking
	public String post(String uri) throws Exception {
		String requestData = "Data from client";

		BodyPublisher bp = BodyPublishers.ofString(requestData);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.POST(bp)
				.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		return response.body();
	}
}
