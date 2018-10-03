package com.lm.springaop.aop.threadlocal;

public class SimpleThreadLocal {

    //使用ThreadLocal保存Integer变量
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            //为Integer变量赋初始值
            return 0;
        }
    };

    public int getNextNum () {
        threadLocal.set (threadLocal.get() +1);
        return threadLocal.get();
    }

    public static void main(String[] args){
        SimpleThreadLocal an= new SimpleThreadLocal();
        TestClient t1 = new TestClient(an);
        TestClient t2 = new TestClient(an);
        TestClient t3 = new TestClient(an);
        t1.start() ;
        t2.start() ;
        t3.start() ;
    }

    private static class TestClient extends Thread{
        private SimpleThreadLocal sn;
        public TestClient(SimpleThreadLocal sn) {
            this.sn = sn;
        }
        public void run(){
            for (int i=0;i< 3;i++){
                System.out.println("thread["+Thread.currentThread().getName()+"] sn["+sn.getNextNum()+"]");
            }
        }
    }

}
