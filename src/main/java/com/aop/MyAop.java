package com.aop;

import com.annotation.clcTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author luokui
 * @date 2022/2/8 10:30
 */
@Component
@Aspect
public class MyAop {
    Logger logger = LoggerFactory.getLogger(MyAop.class);
    //通过非注解的方式
    @Pointcut("execution( * com.controller.*.*(..))")
    public void pointCut(){}

    //通过注解的方式
    @Pointcut("@annotation(com.annotation.clcTime)")
    public void ann(){}

    @Around("ann()")
    public void arroundAnn(ProceedingJoinPoint pjp){
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        clcTime declaredAnnotation = signature.getMethod().getDeclaredAnnotation(clcTime.class);
        String taskType = declaredAnnotation.taskType();
        String step = declaredAnnotation.step();
        double startTime = System.currentTimeMillis();
        logger.info(taskType);
        logger.info(step);
        logger.info(String.valueOf(startTime));
    }

    @Around("pointCut()")
    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
        logger.info(pjp.getSignature().getName());
        Object result = pjp.proceed();
        return result;
    }
}
