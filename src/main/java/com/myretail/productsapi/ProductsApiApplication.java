package com.myretail.productsapi;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class ProductsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder();
	}

	@Bean
	public MongoTemplate mongoTemplate() throws IOException {
		EmbeddedMongoFactoryBean mongoDb = new EmbeddedMongoFactoryBean();
		mongoDb.setBindIp("localhost");
		MongoClient mongoDbClient = mongoDb.getObject();
		return new MongoTemplate(mongoDbClient, "myRetailMongoDb");

	}
}
