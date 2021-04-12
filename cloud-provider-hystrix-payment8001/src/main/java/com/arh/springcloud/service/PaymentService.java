package com.arh.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * 节省时间，不写接口
 */
@Service
public class PaymentService {

    public String executionSucceed(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "，executionSucceed，id：" + id;
    }

    @HystrixCommand(fallbackMethod = "executionTimeOutFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
    public String executionTimeOut(Integer id) {
        int time = 0;
//        try {
//            TimeUnit.SECONDS.sleep(time);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "线程池：" + Thread.currentThread().getName() + "，executionTimeOut！！ id：" + id + "，耗时：" + time;
    }

    public String executionTimeOutFallBack(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "，8001系统繁忙，id：" + id;
    }

    ///////////////////////////服务熔断///////////////////////////

    /**
     * 当请求失败率达到一定情况下，打开熔断器，请求不再调用当前服务，内部设置时钟一般为MTTR(平均故障处理时间)，当打开时长达到所设时钟则进入半熔断状态。
     * 熔断器打开状态下，sleepWindowInMilliseconds表示拒绝请求到再次尝试请求并决定回路是否继续打开的时间
     * 熔断半开：部分请求根据规则调用当前服务，如果请求成功且符合规则则认为当前服务恢复正常，关闭熔断。
     *
     * 这里例子就是不断传负数id让他一直报错走fallback方法，发生熔断
     * 统计肯定需要在一个事件范围内，用metricsRollingStatisticalWindow控制
     */

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }

}
