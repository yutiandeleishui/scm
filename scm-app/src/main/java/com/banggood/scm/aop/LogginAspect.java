package com.banggood.scm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/30.
 */
@Aspect
@Component
public class LogginAspect {
    private static Logger log = LoggerFactory.getLogger(LogginAspect.class);

    @Pointcut(value = "execution(* com.banggood.scm.controller.HomeController.*(..))")
    public  void controllerExcution(){}

    @Before(value = "controllerExcution()")
    public void  beforeMthod(JoinPoint joinPoint){
        String name=joinPoint.getSignature().getName();
        System.out.println("The method"+name+" begins");
        log.info("The method {} begins",name);
    }

    @After(value = "controllerExcution()")
    public void  afterMthod(JoinPoint joinPoint){
        String name=joinPoint.getSignature().getName();
        log.info("The method {} end",name);
    }
}
