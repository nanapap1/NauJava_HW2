package hw2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpHead {

    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/headers"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            ObjectMapper mapper = new ObjectMapper();
            Map<?, ?> json = mapper.readValue(response.body(), Map.class);

            Map<String, String> headers = (Map<String, String>) json.get("headers");

            String headerNames = headers.keySet().stream()
                    .collect(Collectors.joining(", "));

            System.out.println(headerNames);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
