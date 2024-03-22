package Z.Z02.ApiConnectionHandling;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class WeatherApi implements ApiCallable<WeatherApi.WeatherData> {
    private final String city;
    private final CountryApi.CountryData country;
    private final static String API_KEY = "0f29eee92e0b2a1cd24cb5f8faac3f08";

    public WeatherApi(String city, CountryApi.CountryData country) {
        this.city = city;
        this.country = country;
    }


    @Override
    public String getApiEndpoint() {
        return String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=%s", this.city, country.code(), WeatherApi.API_KEY);
    }

    public record WeatherData(double temperature, String wetherDescription, double pressure) { }

    @Override
    public WeatherData getData(JsonNode jsonNode) throws ApiCallException{
        try {
            return new WeatherData(
                    jsonNode.get("main")
                            .get("temp")
                            .doubleValue() - 273.15,
                    jsonNode.get("weather")
                            .get(0)
                            .get("main")
                            .textValue(),
                    jsonNode.get("main")
                            .get("pressure")
                            .doubleValue());
        } catch (Exception e) {
            throw new ApiCallException("Error while parsing weather data");
        }

    }
}


