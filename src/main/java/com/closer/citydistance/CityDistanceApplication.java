package com.closer.citydistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.closer.citydistance.repository")
@EnableTransactionManagement
@EntityScan(basePackages="com.closer.citydistance.model")
public class CityDistanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityDistanceApplication.class, args);
    }

}
