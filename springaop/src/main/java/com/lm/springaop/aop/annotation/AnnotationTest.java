package com.lm.springaop.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //声明注解的保留期限
@Target(ElementType.METHOD)         //声明实用该注解的目标类型
public @interface AnnotationTest {  //@interface定义注解

    boolean value() default true;   //声明注解成员 及其默认值
}
