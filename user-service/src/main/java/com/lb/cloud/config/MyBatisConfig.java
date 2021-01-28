package com.lb.cloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
        * MyBatis配置类
        * Created by macro on 2019/4/8.
        */
@Configuration
@MapperScan("com.lb.cloud.mapper")
public class MyBatisConfig {
}