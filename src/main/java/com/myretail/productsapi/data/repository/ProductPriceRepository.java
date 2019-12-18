package com.myretail.productsapi.data.repository;

import com.myretail.productsapi.data.document.ProductPrice;
import org.springframework.data.repository.CrudRepository;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, Integer> {

}
