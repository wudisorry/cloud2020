package com.arh.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String executionSucceed(Integer id) {
        return "消费端 全局处理 fallback PaymentFallbackService#executionSucceed";
    }

    @Override
    public String executionTimeOut(Integer id) {
        return "消费端 全局处理 fallback PaymentFallbackService#executionTimeOut";
    }
}
