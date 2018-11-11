package com.lm.springthread.service.impl.javathread.AQSfence;

import java.util.concurrent.*;

/**
 * 线程池中的栅栏
 * 多线程中有三个类，分别是CountDownLatch，CyclicBarrier，Semaphore。代表着线程中的栅栏。共享锁。
 * */

public class CountdownLatchExample {

    /**
     * 第一个例子
     * */

    //维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，
    // 那些因为调用 await() 方法而在等待的线程就会被唤醒。
    public static void main0(String[] args) throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.print("run"+ finalI +"..");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end");
        executorService.shutdown();
    }

    /**
     * 第二个例子
     * */
    private final static int SIZE = 8;

    //创建门栓，栅栏
    private static CountDownLatch countDownLatch;

    //创建线程池
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2, 6, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3));

    public static void main(String[] args) {
        countDownLatch = new CountDownLatch(SIZE);

        for (int i = 0; i < 4; i++) {
            threadPoolExecutor.execute(new ThreadTest1());
            threadPoolExecutor.execute(new ThreadTest2());
        }

        try {
            //若countDownLatch不为0.则就不会执行下面内容，会一直阻塞
            // 阻塞等待
            countDownLatch.await();
            System.out.printf("thread main = [{%s}] \n", Thread.currentThread().getName());
            threadPoolExecutor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadTest1 extends Thread {

        @Override
        public void run() {
            System.out.printf("thread-before1 = [%s] \n", Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("thread-after1 =[%s] 执行 \n", Thread.currentThread().getName());
            // 数值减一
            countDownLatch.countDown();
        }
    }

    static class ThreadTest2 extends Thread {

        @Override
        public void run() {
            System.out.printf("thread-before2 = [%s] \n", Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("thread-after2 =[%s] 执行 \n", Thread.currentThread().getName());
            // 数值减一
            countDownLatch.countDown();
        }
    }

}
