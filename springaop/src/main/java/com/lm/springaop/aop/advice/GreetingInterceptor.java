package com.lm.springaop.aop.advice;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//环绕增强
public class GreetingInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("前置增强：增强目标类的方法：" + method.getName() +
                ",目标类方法入参：" + objects.toString() + ",目标类实例：" + o + "代理类实例" + methodProxy);

        System.out.println("环绕增强：执行目标方法前");
        methodProxy.invokeSuper(o, objects);
        System.out.println("环绕增强：执行目标方法后");

        return objects;
    }
}
