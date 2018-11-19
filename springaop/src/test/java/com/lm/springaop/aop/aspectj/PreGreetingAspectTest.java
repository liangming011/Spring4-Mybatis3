package com.lm.springaop.aop.aspectj;

import com.lm.springaop.BaseJunit4Test;
import com.lm.springaop.aop.aspectj.example.NaiveWaiter;
import com.lm.springaop.aop.aspectj.example.Waiter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PreGreetingAspectTest extends BaseJunit4Test {

    Waiter waiter = new NaiveWaiter();


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void beforeGreeting() throws Exception {
        //AspectJ提供的代理工厂
        AspectJProxyFactory factory = new AspectJProxyFactory();
        //1.设置目标对象
        factory.setTarget(waiter);
        //2.添加切面类
        factory.addAspect(PreGreetingAspect.class);
        //3.生成织入切面的代理对象
        Waiter waiter1 = factory.getProxy();
        waiter1.greetTo("greetTo");
        waiter1.saveTo("saveTo");
    }

    //测试自动装配代理工厂
    @Test
    public void test1() throws Exception {
        String configPath = "spring-aspectj.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter1 = (Waiter) ctx.getBean("waiter");
        waiter1.greetTo("greetTo");
        waiter1.saveTo("saveTo");
    }


    @After
    public void tearDown() throws Exception {
    }
}