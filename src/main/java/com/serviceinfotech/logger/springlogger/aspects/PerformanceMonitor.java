package com.serviceinfotech.logger.springlogger.aspects;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import java.util.Date;

public class PerformanceMonitor extends AbstractMonitoringInterceptor {

    public PerformanceMonitor() {
    }

    @Override
    protected Object invokeUnderTrace(MethodInvocation methodInvocation, Log log) throws Throwable {
        String name = createInvocationTraceName(methodInvocation);
        long start = System.currentTimeMillis();
        log.info("Method " + name + " execution started at:" + new Date());
        try {
            return methodInvocation.proceed();
        }
        finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            log.info("Method "+name+" execution lasted:"+time+" ms");
            log.info("Method "+name+" execution ended at:"+new Date());

            if (time > 10){
                log.warn("Method execution longer than 10 ms!");
            }
        }
    }


    public PerformanceMonitor(boolean useDynamicLogger) {
        setUseDynamicLogger(useDynamicLogger);
    }


}