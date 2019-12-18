package com.myretail.productsapi.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myretail.productsapi.data.document.ProductPrice;
import com.myretail.productsapi.data.repository.ProductPriceRepository;
import com.myretail.productsapi.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DataScrapingService {

    private RestTemplate restTemplate;
    private ProductPriceRepository productPriceRepository;

//    public static final List<String> PRODUCT_ID_LIST = Collections.unmodifiableList(
//            new ArrayList<String>() {{
//                add("15117729");
//                add("16483589");
//                add("16696652");
//                add("16752456");
//                add("15643793");
//            }});
            public static final List<Integer> PRODUCT_ID_LIST = Collections.unmodifiableList(
            new ArrayList<Integer>() {{
                add(13860428);
                add(13860427);
            }});

    public DataScrapingService(RestTemplate restTemplate, ProductPriceRepository productPriceRepository) {
        this.restTemplate = restTemplate;
        this.productPriceRepository = productPriceRepository;
    }

    @PostConstruct
    public void populateMongoDB() {
        for(Integer id: PRODUCT_ID_LIST) {
            productPriceRepository.save(
                    new ProductPrice(id, getItemPriceById(id), Constants.CURRENCY_CODE)
            );
        }
    }

    public Double getItemPriceById(Integer id) {

        JsonObject restString = convertJSONStringToJSON(restTemplate
                .getForObject(Constants.MY_RETAIL_URI_PREFIX + id + Constants.MY_RETAIL_URI_SUFFIX, String.class));

        JsonObject productJSON = (JsonObject) restString.get("product");
        JsonObject priceJSON = (JsonObject) productJSON.get("price");
        JsonObject listPriceJSON = (JsonObject) priceJSON.get("listPrice");
        return listPriceJSON.get("price").getAsDouble();
    }

    public JsonObject convertJSONStringToJSON(String jsonString) {
        return (JsonObject) JsonParser.parseString(jsonString);
    }
}
