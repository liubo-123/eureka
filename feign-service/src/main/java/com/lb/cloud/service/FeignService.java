package com.lb.cloud.service;

import org.springframework.stereotype.Component;
@Component
public class FeignService implements IFeignService{
    @Override
    public String query() {
        return "hello feign";
    }
}
