package com.lm.springthread.service.impl.javathread.AQSfence;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池中的栅栏
 * 多线程中有三个类，分别是CountDownLatch，CyclicBarrier，Semaphore。代表着线程中的栅栏。共享锁。
 * */
public class CyclicBarrierExample {

    /**
     * 第一个例子
     * */
    //CyclicBarrier 和 CountdownLatch 的一个区别是，CyclicBarrier 的计数器通过调用 reset() 方法可以循环使用，所以它才叫做循环屏障。
    public static void main0(String[] args) {
        final int totalThread = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            final int finali = i;
            executorService.execute(() -> {
                System.out.println(finali+"before..");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(finali+"after..");
            });
        }
        executorService.shutdown();
    }

    /**
     * 第二个例子
     * */
    private static int SIZE = 9;

    private static CyclicBarrier cyclicBarrier;

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(SIZE);
        for (int i = 0; i < 4 ;i++){
            new ThreadTest1().start();
        }
        for (int i = 0; i < 5 ;i++){
            new ThreadTest2().start();
        }

    }

    //每个ThreadTest执行5次，一起就是10次，就可以不阻塞，执行线程。
    static class ThreadTest1 extends Thread {
        @Override
        public void run() {
            System.out.printf("thread1=[%s] 开始-- \n", Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
                cyclicBarrier.await();
                System.out.printf("thread1=[%s] 继续--- \n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadTest2 extends Thread {
        @Override
        public void run() {
            System.out.printf("thread2=[%s] 开始-- \n", Thread.currentThread().getName());

            try {
                Thread.sleep(2000);
                cyclicBarrier.await();
                System.out.printf("thread2=[%s] 继续--- \n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
