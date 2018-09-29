package com.lm.springtest.controller;

import com.alibaba.fastjson.JSONObject;
import com.lm.springtest.BaseJunit4Test;
import com.lm.springtest.entity.Type;
import com.lm.springtest.entity.User;
import com.lm.springtest.service.TypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

public class TypeControllerJunit4Test extends BaseJunit4Test {

    //MockMvc对我们的系统的Controller进行单元测试
    protected MockMvc mockMvc;

    //mock模拟session
    private MockHttpSession session;

    //mock模拟request
    private MockHttpServletRequest request;

    @Autowired
    protected WebApplicationContext wac;



    @Before
    public void setUp() throws Exception {
        this.session = new MockHttpSession();
        this.request = new MockHttpServletRequest();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  //初始化MockMvc对象
    }

    @Test
    public void selectType() throws Exception {
        //创建参数
        User user = new User();
        user.setUsername("Tom");
        //给session添加参数
        session.setAttribute("user",user);
        //给request添加参数
        request.setSession(session);
        //创建实体类参数
        Type type = new Type();
        type.setIdString("test1");
        //创建modelMap参数
        ModelMap modelMap = new ModelMap();
        //…………

        String requestJson = JSONObject.toJSONString(type);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/type/selectType")//请求的url,请求的方法是get
                                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//数据的格式
                                                //.param("type",type)//添加参数
                                                .content(requestJson)//添加实体参数
                                                .session(session)
                                                ).andExpect(MockMvcResultMatchers.status().isOk()//返回的状态是200
                                                ).andDo(MockMvcResultHandlers.print()//打印出请求和相应的内容
                                                ).andReturn().getResponse().getContentAsString();//将相应的数据转换为字符串

        System.out.println("返回值：" + responseString);

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("this is tearDownAfter()...");
    }


}