package com.lm.springaop.aop.advice;

import com.lm.springaop.BaseJunit4Test;
import com.lm.springaop.entity.Type;
import com.lm.springaop.service.TypeService;
import com.lm.springaop.service.impl.TypeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class GreetingAfterAdviceTest extends BaseJunit4Test {

    @Resource
    private TypeService typeService;

    private Type type;

    @Before
    public void setUp() throws Exception {
        type = new Type("test1");
    }

    //前置增强，测试类这种方式可以更改为配置文件中
    @Test
    public void selectType() throws Exception {

        //引入增强
        BeforeAdvice beforeAdvice = new GreetingBeforeAdvice();
        //spring提供的代理工厂
        ProxyFactory proxyFactory  = new ProxyFactory();
        //设置代理目标对象
        proxyFactory.setTarget(typeService);
        //说明是对接口进行代理，而不是具体的类，代理实现类时，不需要
        proxyFactory.setInterfaces(typeService.getClass().getInterfaces());
        //为代理目标对象添加增强
        proxyFactory.addAdvice(beforeAdvice);
        //使用 cglib 创建接口代理，默认 false则使用 jdk 动态代理
        proxyFactory.setOptimize(true);
        //生成代理实例
        typeService = (TypeService) proxyFactory.getProxy();

        Type type1 = typeService.selectType(type);
        System.out.println(type1.toString());
    }

    //测试配置文件中配置的 aop 此方法失败：原因是扫描 TypeService 无法扫描到
    @Test
    public void test() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        typeService = (TypeService) ctx.getBean("proxyFactory1");
        Type type2 = typeService.selectType(type);
        System.out.println(type2.toString());
    }
}