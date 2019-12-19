package com.myretail.productsapi.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myretail.productsapi.data.document.ProductPrice;
import com.myretail.productsapi.data.repository.ProductPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.myretail.productsapi.utils.Constants.*;
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
        output.addProperty(ID, id);
        output.add(NAME, fetchProductName(id));
        output.add(CURRENT_PRICE, fetchProductPrice(id));
        return output.toString();
    }

    private JsonObject fetchProductPrice(final Integer id) {
        final JsonObject currentPrice = new JsonObject();
        final ProductPrice productPriceInfo = productPriceRepository.findById(id).orElse(null);

        if(productPriceInfo == null) return null;

        currentPrice.addProperty(VALUE, productPriceInfo.getValue());
        currentPrice.addProperty(CURRENCY_CODE, productPriceInfo.getCurrency_code());
        return currentPrice;
    }

    private JsonElement fetchProductName(final Integer id) {
        final JsonObject restString = getProductData(restTemplate, id);

        if(restString.size() == 0) return null;

        JsonObject productJSON = (JsonObject) restString.get(PRODUCT);
        JsonObject itemJSON = (JsonObject) productJSON.get(ITEM);
        JsonObject productDescJSON = (JsonObject) itemJSON.get(PRODUCT_DESCRIPTION);
        return productDescJSON.get(TITLE);
    }
}
