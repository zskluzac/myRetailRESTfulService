package com.myretail.productsapi.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myretail.productsapi.data.document.ProductPrice;
import com.myretail.productsapi.data.repository.ProductPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.myretail.productsapi.utils.RestUtils.getProductData;

@Service
public class OutboundDataCoordinationService {

    private RestTemplate restTemplate;
    private ProductPriceRepository productPriceRepository;

    public OutboundDataCoordinationService(RestTemplate restTemplate, ProductPriceRepository productPriceRepository) {
        this.restTemplate = restTemplate;
        this.productPriceRepository = productPriceRepository;
    }

    public String getProductInfoById(Integer id) {
        JsonObject output = new JsonObject();
        output.addProperty("id", id);
        output.add("name", fetchProductName(id));
        output.add("current_price", fetchProductPrice(id));
        return output.toString();
    }

    private JsonObject fetchProductPrice(final Integer id) {
        final JsonObject currentPrice = new JsonObject();
        final ProductPrice productPriceInfo = productPriceRepository.findById(id).orElse(null);

        if(productPriceInfo == null) return null;

        currentPrice.addProperty("value", productPriceInfo.getPrice());
        currentPrice.addProperty("currency_code", productPriceInfo.getCurrencyCode());
        return currentPrice;
    }

    private JsonElement fetchProductName(final Integer id) {
        final JsonObject restString = getProductData(restTemplate, id);

        if(restString.size() == 0) return null;

        JsonObject productJSON = (JsonObject) restString.get("product");
        JsonObject itemJSON = (JsonObject) productJSON.get("item");
        JsonObject productDescJSON = (JsonObject) itemJSON.get("product_description");
        return productDescJSON.get("title");
    }
}
