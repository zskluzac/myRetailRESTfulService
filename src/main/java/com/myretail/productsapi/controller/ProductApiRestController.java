package com.myretail.productsapi.controller;

import com.myretail.productsapi.service.OutboundDataCoordinationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiRestController{

    public OutboundDataCoordinationService outboundDataCoordinationService;

    public ProductApiRestController(OutboundDataCoordinationService outboundDataCoordinationService) {
        this.outboundDataCoordinationService = outboundDataCoordinationService;
    }

    @RequestMapping("/products/{id}")
    public String getProductById(@PathVariable Integer id) {
        return outboundDataCoordinationService.getProductInfoById(id);
    }
}
