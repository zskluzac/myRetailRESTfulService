package com.myretail.productsapi.controller;

import com.myretail.productsapi.service.DataScrapingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiRestController{

    public DataScrapingService dataScrapingService;

    public ProductApiRestController(DataScrapingService dataScrapingService) {
        this.dataScrapingService = dataScrapingService;
    }

    @RequestMapping("/products/{id}")
    public Double getProductById(@PathVariable String id) {
        return dataScrapingService.getItemPriceById(id);
    }
}
