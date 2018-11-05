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
 */
@Service
public class TypeServiceImpl1 implements TypeService {

    @Autowired
    private TypeDao typeDao;

    private ExecutorService executorService;

    public TypeServiceImpl1() {
        //创建线程池的大小
        executorService = Executors.newFixedThreadPool(5);
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
        Runnable runnable = new MultithreadingRunnable(type);
        Thread thread = new Thread(runnable);
        thread.start();

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
        //创建任务
        List<Future> taskList = new ArrayList<>();
        //在线程池执行任务并获取Future对象
        Future<Object> future = executorService.submit(callable);
        //阻塞线程实时获取结果
        boolean result = (boolean) future.get();
        //添加到任务列表
        taskList.add(future);
        //关闭线程池
        executorService.shutdown();

        //实现 Callable 接口、 ExecutorService接口和Future接口实现有返回结果的线程,
        //可以通过.get阻塞线程等待返回值查看，
        //也可以通过线程完成后查看Future对象，得到异步操作结果。
        return result;


    }

}
