package com.arh.springcloud.controller;

import com.arh.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String executionSucceed(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.executionSucceed(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "executionTimeOutFallBack", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String executionTimeOut(@PathVariable("id") Integer id) {
//        int a= 10/0;
        String result = paymentHystrixService.executionTimeOut(id);
        return result;
    }

    public String executionTimeOutFallBack(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "，这里是消费端，提供者8001系统繁忙，id：" + id;
    }

    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod()
    {
        return "这里是消费端，Global异常处理信息，请稍后再试";
    }
}
