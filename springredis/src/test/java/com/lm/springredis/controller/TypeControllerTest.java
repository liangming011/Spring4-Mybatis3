package com.lm.springredis.controller;

import com.lm.springredis.BaseJunit4Test;
import com.lm.springredis.entity.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


public class TypeControllerTest extends BaseJunit4Test {

    //MockMvc对我们的系统的Controller进行单元测试
    protected MockMvc mockMvc;

    //mock模拟session
    private MockHttpSession session;

    //mock模拟request
    private MockHttpServletRequest request;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private TypeController typeController;

    @Before
    public void setUp() throws Exception {
        this.session = new MockHttpSession();
        this.request = new MockHttpServletRequest();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  //初始化MockMvc对象
    }

    @Test
    public void selectType() throws Exception {

        //创建实体类参数
        Type type = new Type();
        type.setIdString("test1");

        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/type/selectType")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//数据的格式
                .flashAttr("type",type)//添加实体参数
                .session(session)
        ).andExpect(MockMvcResultMatchers.status().isOk()//返回的状态是200
        ).andDo(MockMvcResultHandlers.print()//打印出请求和相应的内容
        ).andReturn().getResponse().getContentAsString();//将相应的数据转换为字符串

        System.out.println("返回值：" + responseString);
    }

    @Test
    public void deleteType()  throws Exception{
    }

    @Test
    public void updateType()  throws Exception{
    }

    @Test
    public void insertType()  throws Exception{
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("this is tearDownAfter()...");
    }
}