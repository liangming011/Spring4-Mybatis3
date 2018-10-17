package com.lm.springaop.aop.aspectj.example;

public class SmartSeller implements Seller {

    @Override
    public void sell(String goods) {

        System.out.println("SmartSeller:sell " + goods + "...");
    }
}
