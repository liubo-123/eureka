//package com.lb.cloud.aspect;
//
//import com.lb.cloud.anno.IsOk;
//import com.lb.cloud.controller.UserController;
//import com.lb.cloud.exception.MyException;
//import com.lb.cloud.util.JsonUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
//@Component
//@Aspect
//public class UserAspect {
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//
//    @Pointcut("execution(* com.lb.cloud.service.*.*(..))")
//    public void queryUser(){
//    }
//    @Around("queryUser()")
//    public Object afterMethod(ProceedingJoinPoint point){
//        Signature signature =point.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        Method method = methodSignature.getMethod();
//        LOGGER.info("method"+ JsonUtils.toJson(method));
//        try {
//            if(method.isAnnotationPresent(IsOk.class)){
//                LOGGER.info("1111"+method.isAnnotationPresent(IsOk.class));
//                Object proceed = point.proceed();
//                LOGGER.info("proceed"+JsonUtils.toJson(proceed));
//                return proceed;
//            }else{
//                throw new MyException("无查询权限");
//            }
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//
//        }
//        return null;
//    }
//}
