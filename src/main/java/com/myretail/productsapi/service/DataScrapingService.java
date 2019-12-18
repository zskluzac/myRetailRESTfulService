package com.myretail.productsapi.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myretail.productsapi.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataScrapingService {

    private RestTemplate restTemplate;

    public DataScrapingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getItemPriceById(String id) {
        JsonObject restString = convertJSONStringToJSON(restTemplate
                .getForObject(Constants.MY_RETAIL_URI_PREFIX + id + Constants.MY_RETAIL_URI_SUFFIX, String.class));

        JsonObject productJSON = (JsonObject) restString.get("product");
        JsonObject priceJSON = (JsonObject) productJSON.get("price");
        JsonObject listPriceJSON = (JsonObject) priceJSON.get("listPrice");
        Double priceValue = listPriceJSON.get("price").getAsDouble();
        return priceValue.toString();
    }

    public JsonObject convertJSONStringToJSON(String jsonString) {
        return (JsonObject) JsonParser.parseString(jsonString);
    }
}
