package com.lm.springaop.aop.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

//前置增强
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("前置增强：增强目标类的方法："+method.getName()+
                ",目标类方法入参："+objects.toString()+",目标类实例："+o);

    }
}
