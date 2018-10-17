package com.lm.springaop.aop.annotation;

public class AnnotationTestService {

    @AnnotationTest(value = true)
    public void annotationTest1(int formid) {
        System.out.printf("测试模块1" + formid);
    }

    @AnnotationTest(value = false)
    public void annotationTest2(int formid) {
        System.out.printf("测试模块2" + formid);
    }
}
