package com.example.shcrawler.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class,
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
public class MultiDataSourceConfig {

    @Value("${spring.datasource.url}")
    private String jpaDatasourceUrl;
    @Value("${spring.datasource.username}")
    private String jpaDatasourceUsername;
    @Value("${spring.datasource.password}")
    private String jpaDatasourcePassword;

    @Value("${spring.data.mongodb.uri}")
    private String mongoDatasourceUri;
    @Value("${spring.data.mongodb.database}")
    private String mongoDatasourceDatabase;


    // JPA MySQL 데이터 소스 설정
    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(jpaDatasourceUrl);
        dataSource.setUsername(jpaDatasourceUsername);
        dataSource.setPassword(jpaDatasourcePassword);
        return dataSource;
    }

    // MongoDB 데이터 소스 설정
    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClient mongoClient = MongoClients.create(mongoDatasourceUri);
        return new MongoTemplate(mongoClient, mongoDatasourceDatabase);

    }
}