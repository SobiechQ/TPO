package Z.Z02;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Nbp {
    //
    private final String currencyCode;

    public Nbp(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    private String getJson(){
        try (var client = HttpClient.newHttpClient()){
            var request = HttpRequest.newBuilder(new URI(String.format("https://api.nbp.pl/api/exchangerates/rates/c/%s?format=json", currencyCode)))
                    .GET()
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getRate(){
        try {
            var a = this.getJson();
            System.out.println(a);
            return new ObjectMapper().readTree(a)
                    .get("rates")
                    .get(0)
                    .get("ask")
                    .doubleValue();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
