package com.yhh.pratice.thread.threadlocal;

import com.yhh.pratice.thread.util.ThreadUtil;

import java.util.concurrent.*;


/****
 * creator by yhh
 *
 * 验证堆内存设置过小时 多线程创建大对象数组抛出堆内存移除Exception
 *
 * -Xmx256m
 *
 * 结果：验证通过   java.lang.OutOfMemoryError: Java heap space
 *
 */
public class OomThread {

    private final static int MAX_SIZE = 500;

    final static ThreadPoolExecutor poolExecutor
            = new ThreadPoolExecutor(500, 1000, 1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>());

     static class Num{
        private byte[] bytes = new byte[1024*1024*5];
    }

//    private static class MyThread extends Thread{
//        @Override
//        public void run() {
//
//            new Num();
//            System.out.println("当前线程   "+Thread.currentThread().getName()
//            );
//            ThreadUtil.SleepBySend(20);
//        }
//    }

    public static void main(String[] args) {

        for (int i = 0; i <MAX_SIZE ; i++) {

            poolExecutor.execute(new Runnable() {
                public void run() {
                    new Num();
                    System.out.println("use local varaible");
                    ThreadUtil.SleepBySend(50);

                }
            });

        }



        ThreadUtil.SleepBySend(2000);

    }

}
