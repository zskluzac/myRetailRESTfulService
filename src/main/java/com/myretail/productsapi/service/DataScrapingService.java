package com.myretail.productsapi.service;

import com.google.gson.JsonObject;
import com.myretail.productsapi.data.document.ProductPrice;
import com.myretail.productsapi.data.repository.ProductPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import static com.myretail.productsapi.utils.Constants.CURRENCY_CODE;
import static com.myretail.productsapi.utils.Constants.PRODUCT_ID_LIST;
import static com.myretail.productsapi.utils.RestUtils.getProductData;

@Service
public class DataScrapingService {

    private RestTemplate restTemplate;
    private ProductPriceRepository productPriceRepository;


    public DataScrapingService(RestTemplate restTemplate, ProductPriceRepository productPriceRepository) {
        this.restTemplate = restTemplate;
        this.productPriceRepository = productPriceRepository;
    }

    @PostConstruct
    public void populateMongoDB() {
        PRODUCT_ID_LIST.forEach((id) ->
                productPriceRepository.save(
                        new ProductPrice(id, getItemPriceById(id), CURRENCY_CODE)
                )
        );
    }

    public Double getItemPriceById(Integer id) {
        JsonObject jsonString = getProductData(restTemplate, id);

        JsonObject productJSON = (JsonObject) jsonString.get("product");
        JsonObject priceJSON = (JsonObject) productJSON.get("price");
        JsonObject listPriceJSON = (JsonObject) priceJSON.get("listPrice");
        return listPriceJSON.get("price").getAsDouble();
    }
}
