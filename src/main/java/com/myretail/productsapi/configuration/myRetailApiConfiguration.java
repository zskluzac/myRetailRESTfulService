package com.myretail.productsapi.configuration;

import com.mongodb.MongoClient;
import com.myretail.productsapi.error.HttpErrorHandler;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.myretail.productsapi.utils.Constants.LOCALHOST;
import static com.myretail.productsapi.utils.Constants.MONGO_DB_NAME;

@Configuration
public class myRetailApiConfiguration {
    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.errorHandler(new HttpErrorHandler()).build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongoDb = new EmbeddedMongoFactoryBean();
        mongoDb.setBindIp(LOCALHOST);
        MongoClient mongoDbClient = mongoDb.getObject();
        return new MongoTemplate(mongoDbClient, MONGO_DB_NAME);

    }
}
