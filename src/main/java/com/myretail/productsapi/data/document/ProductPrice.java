package com.myretail.productsapi.data.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductPrice {

    @Id
    private String productId;
    private Double price;
    private String currencyCode;

    public ProductPrice(String productId, Double price, String currencyCode) {
        this.productId = productId;
        this.price = price;
        this.currencyCode = currencyCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
