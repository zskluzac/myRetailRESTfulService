package com.myretail.productsapi.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.myretail.productsapi.utils.Constants.EMPTY_JSON;


public class RestUtils {

    public static JsonObject getProductData(final RestTemplate restTemplate, final Integer id) {
        ResponseEntity<String> response = restTemplate.getForEntity(
                Constants.MY_RETAIL_URI_PREFIX + id + Constants.MY_RETAIL_URI_SUFFIX,
                String.class
        );

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return (JsonObject) JsonParser.parseString(response.getBody());
        }
        return (JsonObject) JsonParser.parseString(EMPTY_JSON);
    }
}
