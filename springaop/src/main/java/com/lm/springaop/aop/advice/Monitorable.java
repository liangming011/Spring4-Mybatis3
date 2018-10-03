package com.lm.springaop.aop.advice;

//引介增强：通过该接口方法控制业务类性能监视功能的激活和关闭状态
public interface Monitorable {

    void setMonitorableAdvice(boolean active);
}
