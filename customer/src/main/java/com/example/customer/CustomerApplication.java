package com.example.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication
@EnableCircuitBreaker
public class CustomerApplication {

    @Bean
    @LoadBalanced
    RestTemplate rstTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args){
        SpringApplication.run(CustomerApplication.class,args);
    }
}
