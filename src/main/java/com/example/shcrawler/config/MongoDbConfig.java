package com.example.shcrawler.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDbConfig {
    @Value("${spring.data.mongodb.uri}")
    private String mongoDatasourceUri;
    @Value("${spring.data.mongodb.database}")
    private String mongoDatasourceDatabase;

    @Bean
    MongoClient mongoClient() {
        return MongoClients.create(mongoDatasourceUri);
    }

    // MongoDB 데이터 소스 설정
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, mongoDatasourceDatabase);
    }
}