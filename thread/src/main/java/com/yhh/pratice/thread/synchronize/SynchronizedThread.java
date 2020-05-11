package com.yhh.pratice.thread.synchronize;

public class SynchronizedThread {

    private int con = 0;

    private Integer lock=0;

    private String lock1="lock";

    /***
     * 不加锁
     * 两个线程共享访问同一对象实例
     * 修改成员变量时会造成数据不一致
     */
    private void incr1(){
        for (int i = 0; i <10000 ; i++) {
            con++;
        }
        System.out.println(" 当前线程  "+ Thread.currentThread().getName()+"   n值  " + con);
    }

    /***
     * 加锁 数据一致
     * 加在方法前锁的是该方法所属的对象实例
     */
    private synchronized  void incr2(){
        for (int i = 0; i <10000 ; i++) {
            con++;
        }
        System.out.println(" 当前线程  "+ Thread.currentThread().getName()+"   n值  " + con);

    }

    /****
     * 加锁  数据一致
     * 锁代码块 this表示该方法所属对象实例
     */
    private synchronized  void incr3(){
        synchronized (this){
            for (int i = 0; i <10000 ; i++) {
                con++;
            }
        }

        System.out.println(" 当前线程  "+ Thread.currentThread().getName()+"   n值  " + con);

    }

    /****
     * 用对象的成员变量当作锁
     * 成员变量必须为对象  不能用int 这种基本类型
     */

    private synchronized  void incr4(){
        synchronized (lock){
            for (int i = 0; i <10000 ; i++) {
                con++;
            }
        }

        System.out.println(" 当前线程  "+ Thread.currentThread().getName()+"   n值  " + con);

    }

    /****
     * string 也属于对象
     * 如果string对象不赋值表示空对象 会报错
     */

    private synchronized  void incr5(){
        synchronized (lock1){
            for (int i = 0; i <10000 ; i++) {
                con++;
            }
        }

        System.out.println(" 当前线程  "+ Thread.currentThread().getName()+"   n值  " + con);

    }

    private static class MyThread1 extends Thread{

        private  SynchronizedThread synchronizedThread;

        public void setSynchronizedThread(SynchronizedThread synchronizedThread) {
            this.synchronizedThread = synchronizedThread;
        }

        @Override
        public void run() {
            this.synchronizedThread.incr5();
        }
    }

    private static class MyThread2 extends Thread{

        private  SynchronizedThread synchronizedThread;

        public void setSynchronizedThread(SynchronizedThread synchronizedThread) {
            this.synchronizedThread = synchronizedThread;
        }

        @Override
        public void run() {
            this.synchronizedThread.incr5();
        }
    }


    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i <10 ; i++) {
            SynchronizedThread synchronizedThread = new SynchronizedThread();
            MyThread1 myThread1 = new MyThread1();
            myThread1.setSynchronizedThread(synchronizedThread);
            MyThread2 myThread2 = new MyThread2();
            myThread2.setSynchronizedThread(synchronizedThread);
            myThread1.start();
            myThread2.start();
            Thread.sleep(2000);
        }




    }
}
