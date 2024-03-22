package Z.Z02.ApiConnectionHandling;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface ApiCallable<T> {


    String getApiEndpoint();
    default String getJson() throws ApiCallException {
        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder(new URI(this.getApiEndpoint()))
                    .GET()
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new ApiCallException(e);
        }
    }
    default T getData() throws ApiCallException{
        return this.getData(this.getJsonNode());
    }
    T getData(JsonNode jsonNode) throws ApiCallException;
    default JsonNode getJsonNode() throws ApiCallException {
        try {
            return new ObjectMapper().readTree(this.getJson());
        } catch (IOException e) {
            throw new ApiCallException(e);
        }
    }

}
