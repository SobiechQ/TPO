package Z.Z02.ApiConnectionHandling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NbpApi implements ApiCallable<NbpApi.NbpData> {
    //
    private final String currencyCode;

    public NbpApi(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public NbpApi(CountryApi.CountryData countryData) {
        this(countryData.currency());
    }
    public record NbpData(String code, double rate) {}


    @Override
    public String getApiEndpoint() {
        return String.format("https://api.nbp.pl/api/exchangerates/rates/a/%s/?format=json", this.currencyCode);
    }

    @Override
    public NbpData getData(JsonNode jsonNode) throws ApiCallException{
        try {
            return new NbpData(
                    this.currencyCode,
                    jsonNode.get("rates")
                            .get(0)
                            .get("mid")
                            .doubleValue()
            );
        } catch (Exception e) {
            throw new ApiCallException("Error while parsing exchange data");
        }
    }

}
