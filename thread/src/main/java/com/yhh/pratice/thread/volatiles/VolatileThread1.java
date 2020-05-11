package com.yhh.pratice.thread.volatiles;


import com.yhh.pratice.thread.util.ThreadUtil;

/***
 * creator by yhh
 *
 *  验证一个线程修改volatile变量其他线程是否可见
 *
 *  结论： 可见
 */
public class VolatileThread1 {


    private static  volatile boolean flag;

    private static class MyThread1 extends Thread{
        @Override
        public void run() {

            while(!flag){
                System.out.println("当前线程   "+Thread.currentThread().getName()
                +"    flag :  "+flag);
            }


        }
    }

    private static class MyThread2 extends Thread{
        @Override
        public void run() {

            while(!flag){
                System.out.println("当前线程   "+Thread.currentThread().getName()
                        +"    flag :  "+flag);
            }


        }
    }


    private static class MyThread3 extends Thread{
        @Override
        public void run() {
           flag= true;
        }
    }


    public static void main(String[] args) {

         MyThread1 my1 = new MyThread1();
         MyThread2 my2 = new MyThread2();
         MyThread3 my3 = new MyThread3();
         my1.setName("thread1");
         my1.start();
        my1.setName("thread2");

        my2.start();
        ThreadUtil.SleepBySend(1);
         my3.start();


    }

}
