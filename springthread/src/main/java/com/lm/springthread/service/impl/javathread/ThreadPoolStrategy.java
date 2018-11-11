package com.lm.springthread.service.impl.javathread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executors：仿制线程池策略
 * */
public class ThreadPoolStrategy {

    public static ExecutorService newThreadPoolStrategy1(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10000));//修改为有界队列，且最多为10000个
    }
}
