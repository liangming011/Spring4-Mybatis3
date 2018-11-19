package com.lm.springaop.aop.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

//引介增强：通过该接口方法控制业务类性能监视功能的激活和关闭状态
//Delegatinglntroductionlnterceptor为目标类引入性能监视的可控功能
public class GreetingMonitorableAdvice extends DelegatingIntroductionInterceptor implements Monitorable {

    //用于保存性能监控开关状态
    private ThreadLocal<Boolean> MonitorStatusMap = new ThreadLocal<Boolean>();

    @Override
    public void setMonitorableAdvice(boolean active) {
        MonitorStatusMap.set(active);
    }

    //拦截方法
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object obj = null;
        //对于支持性能监视可控代理通过判断其状态决定是否开启性能监控功能
        if (MonitorStatusMap.get() != null && MonitorStatusMap.get()) {
            System.out.printf(mi.getClass().getName() + "."
                    + mi.getMethod().getName());
            obj = super.invoke(mi);
        } else {
            obj = super.invoke(mi);
        }
        return obj;
    }
}
