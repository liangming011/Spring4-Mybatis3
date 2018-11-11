package com.lm.springthread.service.impl;

import com.lm.springthread.dao.TypeDao;
import com.lm.springthread.entity.Type;
import com.lm.springthread.service.TypeService;
import com.lm.springthread.service.impl.springthread.ThreadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * java spring多线程的使用
 */
//使用@Async来表明，其要获取线程池线程执行任务
@Service
public class TypeServiceImpl2 implements TypeService {

    /**
     * 使用ThreadLocal解决线程安全
     **/
    private static final ThreadLocal<Type> typeThreadLocal = new ThreadLocal<>();

    @Autowired
    private TypeDao typeDao;

    //此是使用xml配置文件方式 通过 id 名称回去 bean
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ThreadConfig threadConfig;

    @Override
    @Async// 通过@Async注解方法表名这个方法是一个异步方法，如果注解在类级别，则表名该类的所有方法都是异步的，
    // 而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
    public void selectType(Type type) throws Exception {
        Executor executor = threadConfig.getAsyncExecutor();
        executor.execute(new Runnable(){
            @Override
            public void run(){
                try {
                    typeDao.selectType(type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.err.println("执行异步任务=====" + type.toString());

    }

    @Override
    @Async
    public boolean insertType(Type type) throws Exception {
        ThreadPoolTaskExecutor executor = threadConfig.getAsyncExecutor();
        //操作线程属相
        FutureTask<Object> futureTask = new FutureTask<Object>(new Callable(){
            @Override
            public Object call() throws Exception{
                return typeDao.insertType(type);
            }
        });
        Thread thread = new Thread(futureTask);
        thread.setName("新增数据操作"+type.getIdString());
        //使用多线程
        Future future = executor.submit(thread);
        //中断已阻塞线程
        thread.interrupt();
        //阻塞线程，同步获取结果
        return (boolean) future.get();
    }

    //不使用@Async注解，使用 xml 配置文件方式。
    // 使用ThreadLocal解决线程安全
    @Override
    public void deleteType(Type type) throws Exception {
        typeThreadLocal.set(type);
        taskExecutor.submit(new Callable<Type>() {
            @Override
            public Type call() throws Exception {
                typeDao.deleteType(typeThreadLocal.get());
                typeThreadLocal.remove();
                return null;
            }
        });
    }

    //使用scope="prototype"使用 bean 原型范围，会被垃圾回收
    @Override
    public void updateType(Type type) throws Exception {
        typeThreadLocal.set(type);
        taskExecutor.submit(new Callable<Type>() {
            @Override
            public Type call() throws Exception {
                typeDao.updateType(typeThreadLocal.get());
                typeThreadLocal.remove();
                return null;
            }
        });
    }

}
