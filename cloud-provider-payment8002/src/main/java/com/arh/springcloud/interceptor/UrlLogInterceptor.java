package com.arh.springcloud.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class UrlLogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("---------------接收到请求，开始打印---------------");
        log.info("请求地址：" + request.getRequestURI());
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        if (parameterMap != null) {
//            parameterMap.forEach((name, value) -> {
//                log.info("参数名：" + name);
//                log.info("值：" + Arrays.toString(value));
//            });
//        }
        log.info("---------------------结束打印---------------------");
        return true;
    }
}
