package com.myretail.productsapi.service;

import com.google.gson.JsonObject;
import com.myretail.productsapi.data.document.ProductPrice;
import com.myretail.productsapi.data.repository.ProductPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import static com.myretail.productsapi.utils.Constants.*;
import static com.myretail.productsapi.utils.RestUtils.getProductData;

@Service
public class MongoDbService {

    private RestTemplate restTemplate;
    private ProductPriceRepository productPriceRepository;


    public MongoDbService(RestTemplate restTemplate, ProductPriceRepository productPriceRepository) {
        this.restTemplate = restTemplate;
        this.productPriceRepository = productPriceRepository;
    }

    @PostConstruct
    public void populateMongoDb() {
        for(int i=PRODUCT_ID_LOWER; i < PRODUCT_ID_UPPER; i++) {
            JsonObject jsonString = getProductData(restTemplate, i);
            if(jsonString.size() != 0) {
                productPriceRepository.save(
                        new ProductPrice(i, getItemPriceById(jsonString), CURRENCY_CODE)
                );
            }
        }
    }

    public Double getItemPriceById(JsonObject jsonString) {
        if(jsonString == null) return null;

        JsonObject productJSON = (JsonObject) jsonString.get(PRODUCT);
        JsonObject priceJSON = (JsonObject) productJSON.get(PRICE);
        JsonObject listPriceJSON = (JsonObject) priceJSON.get(LIST_PRICE);
        return listPriceJSON.get(PRICE).getAsDouble();
    }
}
