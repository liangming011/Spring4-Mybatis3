package com.lm.springmvc.controller;

import com.lm.springmvc.BaseJunit4Test;
import com.lm.springmvc.entity.User;
import com.lm.springmvc.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RequestMappingControllerTest extends BaseJunit4Test {

    @Autowired                      /*注入业务层bean*/
    private UserService userService;

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    //@Transactional   //标明此方法需使用事务
    //@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void name() {
        ApplicationContext asd = new ClassPathXmlApplicationContext();
        System.out.printf(asd.getBean("UserService").toString());
        User user = new User();
        boolean sad = userService.createUser(user);
        System.out.printf("11111");

    }
}