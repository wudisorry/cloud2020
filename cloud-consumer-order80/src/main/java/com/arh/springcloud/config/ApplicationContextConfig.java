package com.arh.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //使用LoadBalanced赋予RestTemplate负载均衡能力，这样在向eureka发请求调用服务提供者集群不会报错
    /**
     * 猜测这个注解对RestTemplate做了处理
     * 因为直接访问http://CLOUD-PAYMENT-SERVICE/payment/get/1是行不通的，不加这个注解访问localhost:/consumer/payment/get/1会报错
     * 报错信息："org.springframework.web.client.ResourceAccessException: I/O error on GET request for \"http://CLOUD-PAYMENT-SERVICE/payment/get/1\": CLOUD-PAYMENT-SERVICE; nested exception is java.net.UnknownHostException
     */
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
