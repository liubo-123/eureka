package com.lb.cloud.service.Impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Service
public class HystrixService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.user-service}")
    private String userService;

    /**
     * fallbackMethod：指定服务降级处理方法；
     * ignoreExceptions：忽略某些异常，不发生服务降级；
     * commandKey：命令名称，用于区分不同的命令；
     * groupKey：分组名称，Hystrix会根据不同的分组来统计命令的告警及仪表盘信息；
     * threadPoolKey：线程池名称，用于划分线程池。
     */
    /**
     *  @CacheResult：开启缓存，默认所有参数作为缓存的key，cacheKeyMethod可以通过返回String类型的方法指定key；
     *  @CacheKey：指定缓存的key，可以指定参数或指定参数中的属性值为缓存key，cacheKeyMethod还可以通过返回String类型的方法指定；
     *  @CacheRemove：移除缓存，需要指定commandKey。
     * @return
     */
    @HystrixCommand(fallbackMethod = "getDefaultQuery")
    public String query() {
        return restTemplate.getForObject(userService + "/hello/provide", String.class);
    }
    public String getDefaultQuery() {
        return "hello-hystrix";
    }
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultQuery", commandKey = "getCacheKey")
    public String getUserCache() {
        return restTemplate.getForObject(userService + "/hello/provide", String.class);
    }
    public String getCacheKey() {
        return "hello-CacheKey";
    }
}
