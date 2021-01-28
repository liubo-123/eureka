package com.lb.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * user-service(调用服务的应用名称)
 */
@FeignClient(value = "user-service",fallback = FeignService.class)
public interface IFeignService {
    @RequestMapping("/hello/provide")
    @ResponseBody
    String query();
}
