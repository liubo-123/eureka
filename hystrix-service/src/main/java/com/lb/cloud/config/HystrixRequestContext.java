package com.lb.cloud.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *在缓存使用过程中，我们需要在每次使用缓存的请求前后对HystrixRequestContext进行初始化和关闭，否则会出现如下异常：
 *
 */
@Component
@WebFilter(urlPatterns = "/*",asyncSupported = true)
public class HystrixRequestContext implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        com.netflix.hystrix.strategy.concurrency.HystrixRequestContext context = com.netflix.hystrix.strategy.concurrency.HystrixRequestContext.initializeContext();
        try {
            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } finally {
            context.close();
        }
    }
}