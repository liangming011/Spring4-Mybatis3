package com.lm.springaop.aop.aspectj;

import com.lm.springaop.aop.aspectj.example.Seller;
import com.lm.springaop.aop.aspectj.example.SmartSeller;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EnableSellerAspect {

    @DeclareParents(value="com.lm.springaop.aop.aspectj.example.NaiveWaiter"//为NaiveWaiter添加挨口实现
            , defaultImpl =SmartSeller.class)//默认的接口实观类
    private Seller seller;

}
