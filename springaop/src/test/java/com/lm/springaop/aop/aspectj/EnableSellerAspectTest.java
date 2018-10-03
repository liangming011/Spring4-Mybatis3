package com.lm.springaop.aop.aspectj;

import com.lm.springaop.aop.aspectj.example.Seller;
import com.lm.springaop.aop.aspectj.example.Waiter;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class EnableSellerAspectTest {


    //测试@AspectJ的引介增强
    @Test
    public void test2() throws Exception {
        String configPath = "spring-aspectj.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter1 = (Waiter) ctx.getBean("waiter1");
        waiter1.greetTo("greetTo");
        waiter1.saveTo("saveTo");
        Seller seller = (Seller) waiter1; //可以成功地进行强制类型装换
        seller.sell("goods");

    }

}