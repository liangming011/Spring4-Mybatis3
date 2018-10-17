package com.lm.springaop.aop.advice;


import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

//后置增强
public class GreetingAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("后置增强：增强目标类的方法：" + method.getName() +
                ",目标类方法入参：" + args.toString() + ",目标类实例：" + target);
    }
}
