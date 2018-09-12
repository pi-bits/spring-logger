package com.serviceinfotech.logger.springlogger.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class LoggerAspect {
    private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Before("execution(* com.serviceinfotech.logger.springlogger.*.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature().getName() + ": {}", " -START- ");
    }

    @After("execution(* com.serviceinfotech.logger.springlogger.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature().toShortString() + ": {}", " -OK- ");
    }

    @AfterReturning(value = "execution(* com.serviceinfotech.logger.springlogger.AmigoService.callAmigo(String))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("{} - {} - returned with value {}", joinPoint.getSignature(), joinPoint.getArgs()[0], result);
    }


    @Bean
    public PerformanceMonitor performanceMonitorInterceptor() {
        return new PerformanceMonitor(true);
    }


    @Bean
    public Advisor monitorAdvisor(PerformanceMonitor performanceMonitorInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(" com.serviceinfotech.logger.springlogger.aspects.LoggerAspect.myMonitor()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor);
    }


    @Pointcut("execution(public String com.serviceinfotech.logger.springlogger.AmigoService.callAmigo(..))")
    public void myMonitor() { }



}
