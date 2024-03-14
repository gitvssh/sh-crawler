package com.example.shcrawler.config;

import java.util.HashMap;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.shcrawler.domain"
)
@EntityScan("com.example.shcrawler.domain")
@RequiredArgsConstructor
public class MySqlDataSourceConfig {

    @Value("${spring.datasource.url}")
    private String jpaDatasourceUrl;
    @Value("${spring.datasource.username}")
    private String jpaDatasourceUsername;
    @Value("${spring.datasource.password}")
    private String jpaDatasourcePassword;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String jpaHibernateDialect;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String jpaHibernateDdlAuto;

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

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.shcrawler.domain");
        em.setPersistenceUnitName("shcrawler");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        //Hibernate 설정
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", jpaHibernateDialect);
        properties.put("hibernate.hbm2ddl.auto", jpaHibernateDdlAuto);

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
        return transactionManager;
    }
}
