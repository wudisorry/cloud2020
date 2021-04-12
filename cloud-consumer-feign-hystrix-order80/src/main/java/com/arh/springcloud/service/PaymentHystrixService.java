package com.arh.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
//使用fallback属性表示全局处理，就不用@DefaultProperties和@HystrixCommand
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    String executionSucceed(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String executionTimeOut(@PathVariable("id") Integer id);

}
