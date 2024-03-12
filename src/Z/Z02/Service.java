/**
 *
 *  @author Sobiech Mikołaj S27233
 *
 */

package Z.Z02;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Service {
    private final String county;

    public Service(String county){
        //od javy 11 http request
        this.county = county;
        try (var client = HttpClient.newHttpClient()){
            var request = HttpRequest.newBuilder(new URI("https://restcountries.com/v3.1/name/polska"))
                    .GET()
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            var resp2 = new ObjectMapper().readTree(response).get(0).toString();
            System.out.println(resp2);
            response = resp2;
            Country country = new ObjectMapper().readValue(
                    response,
                    Country.class);
            System.out.println(country);
//            JsonNode jsonNode = new ObjectMapper().readTree(response);
//            System.out.println(jsonNode.get(0).get("cca2"));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getWeather(String city) {
        return null;
    }

    public Double getRateFor(String currencyCode) {
        return (double) 0;
    }

    public Double getNBPRate() {
        return (double) 0;
    }
}
