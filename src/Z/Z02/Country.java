package Z.Z02;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    @JsonProperty("name")
    private String name; //i want this to store common property string of name node
    @JsonProperty("cca2")
    private String code;
    private Map<String, Map<String, String>> currencies;

    public Country(){

    }

    @JsonProperty("cca2")
    public String getCode() {
        return code;
    }
    @JsonProperty("cca2")
    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Map<String, String>> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Map<String, String>> currencies) {
        this.currencies = currencies;
    }

    @JsonProperty("common.0")
    public String getName() {
        return name;
    }
    @JsonProperty("common.0")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", currencies=" + currencies +
                '}';
    }
}
