package com.myretail.productsapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiRestController{

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable String id) {
        return "empty return";
    }
}
