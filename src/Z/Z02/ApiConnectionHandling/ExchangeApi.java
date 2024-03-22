package Z.Z02.ApiConnectionHandling;

import com.fasterxml.jackson.databind.JsonNode;

public class ExchangeApi implements ApiCallable<ExchangeApi.ExchangeData> {
    private final static String API_KEY = "5caebf6549995b7deb193d81";
    private final String currencyCodeFrom;
    private final String currencyCodeTo;

    public ExchangeApi(String currencyCodeFrom, String currencyCodeTo) {
        this.currencyCodeFrom = currencyCodeFrom;
        this.currencyCodeTo = currencyCodeTo;
    }
    public ExchangeApi(CountryApi.CountryData currencyCodeFrom, String currencyCodeTo) {
        this(currencyCodeFrom.currency(), currencyCodeTo);
    }

    @Override
    public String getApiEndpoint() {
        return String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", API_KEY, this.currencyCodeFrom, this.currencyCodeTo);
    }

    @Override
    public ExchangeData getData(JsonNode jsonNode) throws ApiCallException {
        try {
            return new ExchangeData(this.currencyCodeFrom, this.currencyCodeTo, jsonNode.get("conversion_rate").doubleValue());
        } catch (Exception e) {
            throw new ApiCallException("Error while parsing exchange data");
        }
    }

    public record ExchangeData(String currencyCodeFrom, String currencyCodeTo, double conversionRate) {}

}

