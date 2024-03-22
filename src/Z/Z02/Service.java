/**
 *
 *  @author Sobiech Mikołaj S27233
 *
 */

package Z.Z02;


import Z.Z02.ApiConnectionHandling.NbpApi;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Service {
    public Service(String country) {
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
