package com.lm.springaop.aop.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

public class AnnotationTestServiceTest {


    @Test
    public void test() throws Exception {
        //获取AnnotationTestService对应的Class对象
        Class clazz = AnnotationTestService.class;
        //获取类中所有的成员方法(包括私有的，受保护的，默认的)
        Method[] mArray = clazz.getDeclaredMethods();
        //遍历方法
        for (Method m : mArray) {
            //获取方法上所标注的注解对象
            AnnotationTest at = m.getAnnotation(AnnotationTest.class);
            if (at != null) {
                if (at.value()) {
                    System.out.println("注解的方法对象为 true" + m.getName());
                } else {
                    System.out.println("注解的方法对象为 false" + m.getName());
                }
            }
            System.out.println(m);
        }

    }

}