package com.spring.cloud.service.consumer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
@Slf4j
public class EndpointAdvice {
    @Pointcut("within(com.spring.cloud.service.consumer.controller.*)")
    public void pointCut(){}

    @Before("pointCut()")
    public void reforeRequest(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest)attributes.getRequest();
        //URL
        log.info("url={}", request.getRequestURI());
        //method
        log.info("method={}", request.getMethod());
        //ip
        log.info("ip={}",request.getRemoteAddr());
        //类方法
        log.info("class={} and method name = {}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        //参数
        log.info("参数={}",joinPoint.getArgs());
    }

    @AfterReturning(returning = "object", pointcut = "pointCut()")
    public void doAfterReturn(Object object) {
        log.info("out: {}", object);
    }
}
