package com.lm.springaop.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //1.通过该注解搭PreGreetingAspect标识为
@Component//自动扫描加入容器，无需配置文件加入
public class PreGreetingAspect implements Advice {

    @Before("execution(* greetTo(..)) ") //2. 定义切点和增强类型
    public void beforeGreeting(JoinPoint joinPoint) { //3. 增强的横切逻缉
        System.out.println("How are you");
    }

    @Before("execution(* com.lm.springaop.service.TypeService.select*(..)) ") //2. 定义切点和增强类型
    public void selectTypeAspect(JoinPoint joinPoint) { //3. 增强的横切逻缉
        System.out.println("How are you 入参列表" + joinPoint.getArgs() + "目标对象"
                + joinPoint.getTarget() + "代理对象" + joinPoint.getThis() + "连接点方法签名对象" + joinPoint.getSignature());
    }

    @Around("execution(* greetTo(..)) ") //2. 定义切点和增强类型
    public void AroundGreeting(ProceedingJoinPoint joinPoint) { //3. 增强的横切逻缉
        System.out.println("How are you");
    }

    //ProceedingJoinPoint是JoinPoint的子类：ProceedingJoinPoint只支持环绕增强

    @Before("target(com.lm.springaop.service.TypeService) && args (name,num, ..) ")
    public void bindJoinPointParams(int num, String name) {
        System.out.println("How are you 入参name:" + name + "num:" + num);
    }
}
