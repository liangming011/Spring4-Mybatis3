package com.lm.springthread.service.impl.springthread;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * 这是配置类方式配置线程池，也可通过xml 文件配置线程池
 */

@Configuration  //制定类为配置类
@ComponentScan("com.lm.springthread.service.impl")//通过扫描组件的包，为 service 实现层实现多线程
@EnableAsync    //启用异步任务
public class ThreadConfig implements AsyncConfigurer {

    // Executor 就是一个线程池
    @Override
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数，核心线程会一直存活，即使没有任务需要处理。当线程数小于核心线程数时，
        //即使现有的线程空闲，线程池也会优先创建新线程来处理任务，而不是直接交给现有的线程处理。
        //核心线程在allowCoreThreadTimeout被设置为true时会超时退出，默认情况下不会退出。
        taskExecutor.setCorePoolSize(5);
        //最大线程数:当线程数大于或等于核心线程，且任务队列已满时，线程池会创建新的线程，
        //直到线程数量达到maxPoolSize。如果线程数已等于maxPoolSize，且任务队列已满，则已超出线程池的处理能力，
        //线程池会拒绝处理任务而抛出异常。
        taskExecutor.setMaxPoolSize(10);
        //任务队列容量。从maxPoolSize的描述上可以看出，
        //任务队列的容量会影响到线程的变化，因此任务队列的长度也需要恰当的设置。
        taskExecutor.setQueueCapacity(25);
        //修改饱和策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        //初始化
        taskExecutor.initialize();
        //除此之外还可以配置很多线程池参数
        return taskExecutor;
    }
//keepAliveTime
//当线程空闲时间达到keepAliveTime，该线程会退出，直到线程数量等于corePoolSize。
//如果allowCoreThreadTimeout设置为true，则所有线程均会退出直到线程数量为0。
//allowCoreThreadTimeout
//是否允许核心线程空闲退出，默认值为false。

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
