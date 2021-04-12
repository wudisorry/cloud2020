package com.arh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayMain9000 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9000.class, args);
    }
}
