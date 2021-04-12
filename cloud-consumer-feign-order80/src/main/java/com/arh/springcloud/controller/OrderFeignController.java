package com.arh.springcloud.controller;

import com.arh.springcloud.entities.CommonResult;
import com.arh.springcloud.entities.Payment;
import com.arh.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("进入com.arh.springcloud.controller.OrderFeignController.getPaymentById");
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String testTimeout() {
        log.info("进入com.arh.springcloud.controller.OrderFeignController.testTimeout");
        return paymentFeignService.testTimeout();
    }
}
