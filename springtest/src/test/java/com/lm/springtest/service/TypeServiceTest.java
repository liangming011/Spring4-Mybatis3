package com.lm.springtest.service;

import com.lm.springtest.BaseJunit4Test;
import com.lm.springtest.entity.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class TypeServiceTest extends BaseJunit4Test {


    @Resource
    private TypeService typeService;

    private Type type;

    @Before
    public void setUp() throws Exception {
        type = new Type("test1");
    }

    @Test
    public void selectType() throws Exception {
        Type type1 = typeService.selectType(type);
        System.out.println(type1.toString());
    }

    @After
    public void tearDown() throws Exception {
    }
}