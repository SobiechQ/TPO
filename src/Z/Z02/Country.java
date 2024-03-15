package Z.Z02;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Currency;

public record Country(String name, String code, String currency) {
    public Country(JsonNode node) {
        this(node.get(0).get("name").get("common").textValue(),
                node.get(0).get("cca2").textValue(),
                node.get(0).get("currencies").fieldNames().next()
        );

    }
}
