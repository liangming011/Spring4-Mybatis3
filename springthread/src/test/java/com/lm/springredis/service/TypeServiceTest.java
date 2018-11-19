package com.lm.springredis.service;

import com.lm.springredis.BaseJunit4Test;
import com.lm.springthread.entity.Type;
import com.lm.springthread.service.TypeService;
import com.lm.springthread.service.impl.TypeServiceImpl1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TypeServiceTest extends BaseJunit4Test {

    TypeService typeService = new TypeServiceImpl1();

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
        typeService.selectType(type);
    }


    @After
    public void tearDown() throws Exception {
    }
}