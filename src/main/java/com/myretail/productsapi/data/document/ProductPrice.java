package com.myretail.productsapi.data.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonIgnoreProperties("productId")
public class ProductPrice {

    @Id
    private Integer productId;
    private Double value;
    private String currency_code;

    public ProductPrice(Integer productId, Double value, String currency_code) {
        this.productId = productId;
        this.value = value;
        this.currency_code = currency_code;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }
}
