package com.feifei.carrentalprototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class CarRentalPrototypeApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarRentalPrototypeApplication.class, args);
  }

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.car-rental")
  public DataSourceProperties materialLibDataSource() {
    return new DataSourceProperties();
  }
}
