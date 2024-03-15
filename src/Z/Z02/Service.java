/**
 *
 *  @author Sobiech Mikołaj S27233
 *
 */

package Z.Z02;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Service {
    private final Country country;
    private final Nbp nbp;

    public Service(String country){
        try (var client = HttpClient.newHttpClient()){
            var request = HttpRequest.newBuilder(new URI("https://restcountries.com/v3.1/name/"+country))
                    .GET()
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            this.country = new Country(new ObjectMapper().readTree(response));
            System.out.println(this.country);
            this.nbp = new Nbp(this.country.currency());
            System.out.println(this.nbp.getRate());
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
