package com.lm.springthread.service.impl.javathread;


import com.lm.springthread.dao.TypeDao;
import com.lm.springthread.dao.impl.TypeDaoImpl;
import com.lm.springthread.entity.Type;

/**
 * 实现Runnable接口，并实现该接口的run()方法.创建一个Thread对象，用实现的Runnable接口的对象作为参数实例化Thread对象，调用此对象的start方法。
 **/
public class MultithreadingRunnable implements Runnable {

    private Type type;

    public MultithreadingRunnable() {
    }

    public MultithreadingRunnable(Type type) {
        this.type = type;
    }


    @Override
    public void run() {
        System.out.println("MyRunnable running");
        TypeDao typeDao = new TypeDaoImpl();
        try {
            typeDao.deleteType(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        Thread thread1 = new Thread(new MultithreadingRunnable());
        System.out.println(thread1.currentThread().getName());
        thread1.start();

        //实现了Runnable接口的匿名类
        Runnable myRunnable = new Runnable() {
            public void run() {
                System.out.println("Runnable running");
            }
        };
        Thread thread2 = new Thread(myRunnable);
        thread2.start();
    }
}
