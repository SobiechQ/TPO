/**
 *
 *  @author Sobiech Mikołaj S27233
 *
 */

package Z.Z02;


import Z.Z02.ApiConnectionHandling.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Service {
    private final String country;
    public Service(String country) {
        this.country = country;
    }
    public String getWeather(String city) {
        try {
            return new WeatherApi(city, this.getCountryData()).getJson();
        } catch (ApiCallException e) {
            return "Error while getting weather data";
        }
    }

    public Double getRateFor(String currencyCode) {
        try {
            return new ExchangeApi(this.getCountryData(), currencyCode).getData().conversionRate();
        } catch (ApiCallException e) {
            return -1.0;
        }
    }

    public Double getNBPRate() {
        try {
            return new NbpApi(this.getCountryData()).getData().rate();
        } catch (ApiCallException e) {
            return -1.0;
        }
    }
    private CountryApi.CountryData getCountryData() throws ApiCallException {
        return new CountryApi(this.country).getData();
    }
}
