package com.lm.springthread.service.impl.javathread.AQSfence;

import java.util.concurrent.*;

/**
 * 线程池中的栅栏
 * 多线程中有三个类，分别是CountDownLatch，CyclicBarrier，Semaphore。代表着线程中的栅栏。共享锁。
 * */
public class SemaphoreExample {

    /**
     * 第一个例子
     */
    //Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
    public static void main1(String[] args) {
        final int clientCount = 3;
        final int totalRequestCount = 10;
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            executorService.execute(() -> {
                try {
                    //信号量默认加一（可带int参数）
                    semaphore.acquire();
                    System.out.print(semaphore.availablePermits() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //默认减一（可带int参数）
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }

    /**
     * 第二个例子
     * 在一组线程中，线程持有信号量，如果信息量得大小为10，那么所有线程能够持有得信号量不能超过10，
     * 如果3个线程分别持有得信号量是3 4 5 。 那么只能是两个线程运行，当其中一个释放了该信号量，其他线程才可以运行。
     */
    private static int SIZE = 10;


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(SIZE);
        ExecutorService executorService = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));
        executorService.execute(new ThreadTest(3, semaphore));
        executorService.execute(new ThreadTest(4, semaphore));
        executorService.execute(new ThreadTest(5, semaphore));
        executorService.shutdown();
    }


    static class ThreadTest extends Thread {

        private Integer count;

        private Semaphore semaphore;

        public ThreadTest(Integer count, Semaphore semaphore) {
            this.count = count;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(count);
                System.out.printf("thread=[%s]  拥有信号量 semaphore=[%d] 开始---- \n", Thread.currentThread().getName(), count);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(count);
                System.out.printf("thread=[%s] 释放了 semaphore=[%d] --\n", Thread.currentThread().getName(), count);
            }


        }
    }
}
