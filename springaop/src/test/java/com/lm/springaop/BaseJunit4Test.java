package com.lm.springaop;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

//Junit启动时加载SpringIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//加载spring配置文件
@ContextConfiguration(value = {"classpath:spring-context.xml", "classpath:spring-mvc.xml"})
//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@Transactional
//@webappconfiguration是一级注释，用于声明一个ApplicationContext集成测试加载WebApplicationContext。作用是模拟ServletContext
@WebAppConfiguration
public class BaseJunit4Test {

    /**
     * 以下方法仅说明测试用例所有的测试方法，在BaseJunit4Test中一般不写
     */


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("this is setUpBeforeClass()...");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("this is setUpBefore()...");
    }

    @Test
    public void test1() throws Exception {
    }

    @Test
    public void test2() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("this is tearDownAfter()...");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("this is tearDownAfterClass()...");
    }
}
