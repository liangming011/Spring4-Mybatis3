package com.lm.springthread.service.impl;

import com.lm.springthread.dao.TypeDao;
import com.lm.springthread.entity.Type;
import com.lm.springthread.service.TypeService;
import com.lm.springthread.service.impl.javathread.MultithreadingRunnable;
import com.sun.tools.internal.xjc.addon.sync.SynchronizedMethodAddOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    @Override
    @Async// 通过@Async注解方法表名这个方法是一个异步方法，如果注解在类级别，则表名该类的所有方法都是异步的，
    // 而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
    public void selectType(Type type) throws Exception {
        System.err.println("执行异步任务=====" + type.toString());
        typeDao.selectType(type);
    }

    @Override
    @Async
    public boolean insertType(Type type) throws Exception {
        boolean result = typeDao.insertType(type);
        return result;
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
