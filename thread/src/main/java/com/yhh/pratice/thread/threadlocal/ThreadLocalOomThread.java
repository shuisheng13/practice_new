package com.yhh.pratice.thread.threadlocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***
 * creator by yhh
 * 测试多线程下 threadlocal 放入大对象 导致堆内存溢出
 * -Xmx50m
 * 5个线程 每个线程创建5M大小对象  理论上只会占用25M  实际监控开销了100M左右大小内存
 * 结论：验证通过  threadLocal并不会释放内存 除非手动remove
 *
 *
 */
public class ThreadLocalOomThread {


    private final static int MAX_SIZE = 500;

    final static ThreadPoolExecutor poolExecutor
            = new ThreadPoolExecutor(5, 5, 1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>());

    private static class Num{
        private byte[] bytes = new byte[1024*1024*5];

    }


    private static final ThreadLocal<Num> threadLocal = new ThreadLocal<Num>();

    public static void main(String[] args) {

        for (int i = 0; i <MAX_SIZE ; i++) {

            poolExecutor.execute(new Runnable() {
                public void run() {
                    threadLocal.set(new Num());
                    //threadLocal.remove();
                }
            });
        }
    }



}
