/**
 *
 *  @author Sobiech Mikołaj S27233
 *
 */

package Z.Z02;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Service {
    private final String county;

    public Service(String county){
        //od javy 11 http request
        this.county = county;
        try {
            var req = HttpRequest.newBuilder(new URI("https://restcountries.com/v3.1/name/polska"))
                    .GET()
                    .build();
            //HttpClient todo
        } catch (URISyntaxException e) {
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
