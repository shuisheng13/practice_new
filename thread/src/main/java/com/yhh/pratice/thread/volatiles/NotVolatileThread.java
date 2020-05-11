package com.yhh.pratice.thread.volatiles;

import com.yhh.pratice.thread.util.ThreadUtil;

/****
 * creator by yhh
 *
 * 验证同一变量在一个线程修改后另一个线程是否可见
 *
 * 结论：不可见
 */
public class NotVolatileThread {

    private static boolean flag;


    private static class MyThread1 extends Thread{


        @Override
        public void run() {

            while(flag){
                System.out.println("flag -------->  "+flag);
            }
        }
    }

    private static class MyThread2 extends Thread {


        @Override
        public void run() {
            flag = true;
        }
    }

    public static void main(String[] args) {

        MyThread1 my1 = new MyThread1();

        //MyThread2 my2 = new MyThread2();
        my1.start();
        //ThreadUtil.SleepBySend(1);
        //my2.start();

        ThreadUtil.SleepBySend(1);
        flag=true;
        ThreadUtil.SleepBySend(5);
        System.out.println("主线程运行结束。。。。。。。。。。。");
    }

}
