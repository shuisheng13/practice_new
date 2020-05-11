package com.yhh.pratice.thread.synchronize;


import com.yhh.pratice.thread.util.ThreadUtil;

/****
 *
 * creator by yhh
 * 验证线程休眠时是否会释放锁
 *
 * test1 sleep 不会释放锁
 *
 * test2 yiled 不会释放锁
 *
 *
 * 结论 : 不会
 */
public class SleepThread {



    private synchronized void test1(){

        ThreadUtil.printThreadNameStrar();
        ThreadUtil.SleepBySend(5);
        ThreadUtil.printThreadNameEnd();

    }

    private synchronized void test2(){
        ThreadUtil.printThreadNameStrar();
        ThreadUtil.SleepBySend(5);
        Thread.yield();
        ThreadUtil.printThreadNameEnd();

    }

    private static class MyThread1 extends Thread{

        private SleepThread sleepThread;

        public void setSleepThread(SleepThread sleepThread) {
            this.sleepThread = sleepThread;
        }

        @Override
        public void run() {

            this.sleepThread.test2();

        }
    }


    private static class MyThread2 extends Thread{

        private SleepThread sleepThread;

        public void setSleepThread(SleepThread sleepThread) {
            this.sleepThread = sleepThread;
        }

        @Override
        public void run() {

            this.sleepThread.test2();

        }
    }


    public static void main(String[] args) {
        SleepThread sleepThread = new SleepThread();

        for (int i = 0; i < 10; i++) {
            MyThread1 myThread1 = new MyThread1();
            myThread1.setSleepThread(sleepThread);
            MyThread2 myThread2 = new MyThread2();
            myThread2.setSleepThread(sleepThread);
            myThread1.setName("mythread1 ");
            myThread2.setName("mythread2 ");
            myThread1.start();
            myThread2.start();
        }


    }




}
