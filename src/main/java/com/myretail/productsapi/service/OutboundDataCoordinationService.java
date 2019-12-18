package com.myretail.productsapi.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myretail.productsapi.data.document.ProductPrice;
import com.myretail.productsapi.data.repository.ProductPriceRepository;
import com.myretail.productsapi.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OutboundDataCoordinationService {

    private RestTemplate restTemplate;
    private ProductPriceRepository productPriceRepository;

    public OutboundDataCoordinationService(RestTemplate restTemplate, ProductPriceRepository productPriceRepository) {
        this.restTemplate = restTemplate;
        this.productPriceRepository = productPriceRepository;
    }

    public String getProductInfoById(Integer id) {
        Map<String, Object> output = new HashMap<>();

        output.put("id", id);
        output.put("name", fetchProductName(id));
        output.put("current_price", fetchProductPrice(id));



        return output.toString();
    }

    private Map<String, Object> fetchProductPrice(Integer id) {
        Map<String, Object> currentPrice = new HashMap<>();
        ProductPrice productPriceInfo = productPriceRepository.findById(id).orElse(null);
        currentPrice.put("value", productPriceInfo.getPrice());
        currentPrice.put("currency_code", productPriceInfo.getCurrencyCode());
        return currentPrice;
    }

    private String fetchProductName(Integer id) {
        JsonObject restString = convertJSONStringToJSON(restTemplate
                .getForObject(Constants.MY_RETAIL_URI_PREFIX + id + Constants.MY_RETAIL_URI_SUFFIX, String.class));

        JsonObject productJSON = (JsonObject) restString.get("product");
        JsonObject itemJSON = (JsonObject) productJSON.get("item");
        JsonObject productDescJSON = (JsonObject) itemJSON.get("product_description");
        return productDescJSON.get("title").toString();
    }

    private JsonObject convertJSONStringToJSON(String jsonString) {
        return (JsonObject) JsonParser.parseString(jsonString);
    }
}
