package Z.Z02.ApiConnectionHandling;

import com.fasterxml.jackson.databind.JsonNode;

public class CountryApi implements ApiCallable<CountryApi.CountryData> {
    private final String countryName;
    public CountryApi(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String getApiEndpoint() {
        return String.format("https://restcountries.com/v3.1/name/%s", this.countryName);
    }

    @Override
    public CountryData getData(JsonNode jsonNode) throws ApiCallException {
        try {
            return new CountryData(
                    jsonNode.get(0).get("name").get("common").textValue(),
                    jsonNode.get(0).get("cca2").textValue(),
                    jsonNode.get(0).get("currencies").fieldNames().next()
            );
        } catch (Exception e) {
            throw new ApiCallException("Error while parsing country data");
        }
    }
    public record CountryData(String name, String code, String currency) {}
}
