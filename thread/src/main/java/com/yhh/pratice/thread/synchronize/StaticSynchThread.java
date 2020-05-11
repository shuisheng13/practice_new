package com.yhh.pratice.thread.synchronize;


/***
 * test1 对象锁同步方法
 *
 * test2 类锁同步方法
 *
 * jvm加载对象时一个类只有同一个class文件
 *
 * test3 静态成员变量对象锁
 *
 * test1 和test2 可以同时进行
 *
 * test2 和test3 可以同时进行
 */
public class StaticSynchThread {

    /***
     * 对象锁
     */
    private synchronized void test1(){
        System.out.println("当前线程  " +Thread.currentThread().getName()
        +"  begin ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前线程  " +Thread.currentThread().getName()
                +"  end ");
    }

    /***
     * 类锁  锁的是class文件
     */
    private static synchronized void test2(){

        System.out.println("当前线程  " +Thread.currentThread().getName()
                +"  begin ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前线程  " +Thread.currentThread().getName()
                +"  end ");

    }
    private static Object obj = new Object();

    /***
     * 类锁  锁的是class文件
     */
    private static synchronized void test3(){

        synchronized (obj){
            System.out.println("当前线程  " +Thread.currentThread().getName()
                    +"  begin ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("当前线程  " +Thread.currentThread().getName()
                    +"  end ");
        }
    }

    private static class MyThread1 extends Thread{

        private StaticSynchThread staticSynchThread;

        public void setStaticSynchThread(StaticSynchThread staticSynchThread) {
            this.staticSynchThread = staticSynchThread;
        }

        @Override
        public void run() {

            this.staticSynchThread.test1();
        }
    }

    private static class MyThread2 extends Thread{

        private StaticSynchThread staticSynchThread;

        public void setStaticSynchThread(StaticSynchThread staticSynchThread) {
            this.staticSynchThread = staticSynchThread;
        }

        @Override
        public void run() {

            this.staticSynchThread.test3();
        }
    }

    public static void main(String[] args) {
        StaticSynchThread staticSynchThread = new StaticSynchThread();
        MyThread1 myThread1 = new MyThread1();
        myThread1.setName("thread1");
        myThread1.setStaticSynchThread(staticSynchThread);
        MyThread2 myThread2 = new MyThread2();
        myThread2.setName("thread2");
        myThread2.setStaticSynchThread(staticSynchThread);
        myThread1.start();
        myThread2.start();

    }

}
