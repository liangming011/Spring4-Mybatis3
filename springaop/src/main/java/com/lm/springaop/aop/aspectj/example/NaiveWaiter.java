package com.lm.springaop.aop.aspectj.example;

public class NaiveWaiter implements Waiter {
    @Override
    public void greetTo(String clineName) {
        System.out.println("NaiveWaiter:greet " + clineName + "...");
    }

    @Override
    public void saveTo(String clineName) {
        System.out.println("NaiveWaiter:save " + clineName + "...");

    }
}
