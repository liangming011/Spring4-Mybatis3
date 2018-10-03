package com.lm.springaop.service;

import com.lm.springaop.BaseJunit4Test;
import com.lm.springaop.aop.advice.GreetingBeforeAdvice;
import com.lm.springaop.entity.Type;
import com.lm.springaop.service.impl.TypeServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

public class TypeServiceTest extends BaseJunit4Test {

    TypeService typeService = new TypeServiceImpl();

    private Type type;

    @Before
    public void setUp() throws Exception {
        type = new Type("test1");
    }

    //测试自动装配BeanNameAutoProxyCreator
    @Test
    public void test1() throws Exception {
        String configPath = "spring-context.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        typeService = (TypeService) ctx.getBean("proxyFactory2");
        Type type2 = typeService.selectType(type);
        System.out.println(type2.toString());
    }


    @After
    public void tearDown() throws Exception {
    }
}