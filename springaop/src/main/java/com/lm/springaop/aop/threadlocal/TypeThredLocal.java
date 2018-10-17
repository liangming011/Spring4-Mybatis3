package com.lm.springaop.aop.threadlocal;

import com.lm.springaop.entity.Type;

import java.sql.Connection;
import java.sql.Statement;

public class TypeThredLocal {

    /**
     * Type为非线程安全的对象
     */
    //使用ThreadLocal保存Type变量
    private static ThreadLocal<Type> threadLocal = new ThreadLocal<Type>();

    public static Type getType() {
        //如果threadLocal中没有type的初始值，则创建一个新的Type
        if (threadLocal == null) {
            Type type = new Type();
            type.setIdString("threadLocal");
            threadLocal.set(type);
            return type;
        } else {
            //直接返回线程本地变量
            return threadLocal.get();
        }

    }

}
