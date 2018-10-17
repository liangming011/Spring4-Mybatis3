package com.lm.springaop.aop.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

//抛出异常增强
public class GreetingThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Object o, Method method, Object[] objects, Exception ex) throws Throwable {

        System.out.println("抛出异常增强：增强目标类的方法：" + method.getName() +
                ",目标类方法入参：" + objects.toString() + ",目标类实例：" + o + "抛出异常增强" + ex);
        System.out.println("成功回滚事物");

    }
}
