package com.flowergardenweb.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;


@Configuration
@ComponentScan({"com.flowergardenweb.web", "com.flowergarden"})
@PropertySource("application.properties")
public class ApplicationConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClass}")
    private String driverClass;

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        Class.forName(driverClass);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(jdbcUrl);
        return dataSource;
    }
}
