package com.jachs.aspectj.configer;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhanchaohan
 * 
 */
@Slf4j
@Aspect
@Configuration
public class AspectConfiger {
    
    // 定义切点Pointcut
    @Pointcut("execution(* com.jachs.aspectj.controller.AspectController.*(..))")
    public void pointcut() {
        log.info("pointcut---------------execute");
    }
 
 
    @Before("pointcut()")
    public void before(){
        log.info("@Before------------execute");
    }
 
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Signature signature = joinPoint.getSignature();
        log.info("@Around-joinPoint.proceed(args)前");
        log.info("@Around-执行目标方法前参数:"+Arrays.toString(joinPoint.getArgs()));
        Object[] args = joinPoint.getArgs();
        if(args != null &&args.length>0){
            args[0]="update";
        }
        Object proceed = joinPoint.proceed(args);
        log.info("@Around-joinPoint.proceed(args)后");
        log.info("@Around-被织入的目标对象:" + joinPoint.getTarget());
        log.info("@Around-返回值:"+JSON.toJSONString(proceed));
        return JSON.toJSONString(proceed);
    }
 
    @AfterReturning(value = "pointcut()",returning = "returnValue")
    public void afterReturning(JoinPoint point, Object returnValue) {
        log.info("@AfterReturning-方法:"+point.getSignature().getDeclaringTypeName()+point.getSignature().getName());
        log.info("@AfterReturning-参数:"+Arrays.toString(point.getArgs()));
        log.info("@AfterReturning-返回值:" + returnValue);
        log.info("@AfterReturning-被织入的目标对象:" + point.getTarget());
    }
 
    @After("pointcut()")
    public void after(JoinPoint point){
        log.info("@After-方法:"+point.getSignature().getDeclaringTypeName()+point.getSignature().getName());
        log.info("@After-参数:"+Arrays.toString(point.getArgs()));
        log.info("@After-被织入的目标对象:" + point.getTarget());
    }
    @AfterThrowing("pointcut()")
    public void afterThrowing(JoinPoint point){
        log.info("@AfterThrowing-----",point.getTarget());
    }
}
