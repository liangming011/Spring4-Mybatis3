package com.lm.springthread.service.impl;

import com.lm.springthread.dao.TypeDao;
import com.lm.springthread.entity.Type;
import com.lm.springthread.service.TypeService;
import com.lm.springthread.service.impl.javathread.MultithreadingCallable;
import com.lm.springthread.service.impl.javathread.MultithreadingRunnable;
import com.lm.springthread.service.impl.javathread.MultithreadingThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * java 多线程的使用
 * https://www.oschina.net/question/565065_86540
 */
@Service
public class TypeServiceImpl1 implements TypeService,ThreadFactory {

    @Autowired
    private TypeDao typeDao;

    private ExecutorService executorService;

    /**线程池属性*///多线程中使用
    ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor)executorService;

    public TypeServiceImpl1() {
        //创建线程池的大小,不同的
        executorService = Executors.newFixedThreadPool(5);
        /**修改线程池的属性*/
        threadPoolExecutor.setCorePoolSize(5);//池中所保存的线程数，包括空闲线程
        threadPoolExecutor.setKeepAliveTime(1000,TimeUnit.SECONDS);//当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
        threadPoolExecutor.setMaximumPoolSize(5);//池中允许的最大线程数。
        //修改线程池的饱和策略，多线程中使用
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        return thread;
    }

    //继承 thread 类
    @Override
    public void selectType(Type type) throws Exception {
        Thread thread = new MultithreadingThread(type);
        thread.start();
        //继承 thread 的线程是没有返回值的，只能看日志是否成功
        //这个只是例子，我懒得改了
        //return typeDao.selectType(type);
    }

    //实现 Runable 接口
    @Override
    public void deleteType(Type type) throws Exception {
        //第一种:实现线程的方式
        Runnable runnable = new MultithreadingRunnable(type);
        Thread thread = new Thread(runnable);
        thread.start();

        //第二种:实现线程的方式
        Thread thread1 = newThread(runnable);//使用线程工厂
        thread1.start();

        //第三种:实现线程池的方式
        Thread thread2 = newThread(runnable);//使用线程工厂
        //executorService.execute(runnable);
        executorService.execute(thread2);

        //实现 Runable 接口的线程也是是没有返回值的，只能看日志是否成功
        //这个只是例子，我懒得改了
        //return typeDao.deleteType(type);
    }

    //实现 Callable 接口 和 FutureTask 包装器创建线程
    @Override
    public void updateType(Type type) throws Exception {
        Callable callable = new MultithreadingCallable(type);
        FutureTask<Object> futureTask = new FutureTask<Object>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        //实现 Callable 接口和 FutureTask 包装器创建线程也是是没有返回值的，只能看日志是否成功
        //return typeDao.updateType(type);
    }

    //实现 Callable 接口、 ExecutorService接口和Future接口实现有返回结果的线程
    @Override
    public boolean insertType(Type type) throws Exception {
        //创建线程实例
        Callable callable = new MultithreadingCallable(type);
        //操作单个线程属性
        FutureTask<Object> futureTask = new FutureTask<Object>(callable);
        Thread thread = new Thread(futureTask);
        thread.setPriority(5);
        //创建任务
        List<Future> taskList = new ArrayList<>();
        //在线程池执行任务并获取Future对象
        Future<Object> future = executorService.submit(callable);//executorService.submit(thread)：这是为了操作单个线程属性
        //阻塞线程实时获取结果,同步线程
        boolean result = (boolean) future.get();
        //添加到任务列表
        taskList.add(future);
        /**监控线程池*/
        controlThreadPool();
        //关闭线程池
        executorService.shutdown();

        //实现 Callable 接口、 ExecutorService接口和Future接口实现有返回结果的线程,
        //可以通过.get阻塞线程等待返回值查看，
        //也可以通过线程完成后查看Future对象，得到异步操作结果。
        return result;
    }

    public void controlThreadPool() throws Exception {
        /**监控线程池*/
        threadPoolExecutor.getTaskCount();//线程池需要执行的任务数量
        threadPoolExecutor.getCompletedTaskCount();//线程池在运行过程中已完成的任务数量
        threadPoolExecutor.getPoolSize();//线程池的线程数量
        threadPoolExecutor.getLargestPoolSize();//线程池里曾经创建过的最大线程数量
        threadPoolExecutor.getActiveCount();//获取活动的线程数
    }

}
