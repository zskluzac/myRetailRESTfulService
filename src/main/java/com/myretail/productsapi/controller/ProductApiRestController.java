package com.myretail.productsapi.controller;

import com.myretail.productsapi.data.document.ProductInfo;
import com.myretail.productsapi.data.document.ProductPrice;
import com.myretail.productsapi.data.repository.ProductPriceRepository;
import com.myretail.productsapi.service.OutboundDataCoordinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.myretail.productsapi.utils.Constants.CURRENCY_CODE;

@RestController
public class ProductApiRestController{

    private OutboundDataCoordinationService outboundDataCoordinationService;
    private ProductPriceRepository productPriceRepository;

    public ProductApiRestController(OutboundDataCoordinationService outboundDataCoordinationService,
                                    ProductPriceRepository productPriceRepository) {
        this.outboundDataCoordinationService = outboundDataCoordinationService;
        this.productPriceRepository = productPriceRepository;
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable Integer id) {
        return outboundDataCoordinationService.getProductInfoById(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductInfo> setProductPrice(@RequestBody ProductInfo productInfo, @PathVariable Integer id) {
        productPriceRepository.save(
                new ProductPrice(id, productInfo.getCurrent_price().getValue(), CURRENCY_CODE)
        );
        return ResponseEntity.ok(productInfo);
    }
}
