package com.yhh.pratice.thread.InterruptedExcepiton;


/****
 *
 * creator by yhh
 *
 * 当执行一些阻塞方法如 sleep  wait时会抛出 InterruptedException异常
 *
 * 捕获该异常时线程中断标示会变成false
 *
 * jdk 如何设计是为了当线程阻塞时能够提供手动释放线程资源空间，
 *
 * 如：具体业务执行时可以将一资源释放 再手调用interrupted 线程中断标示会再次成为中断状态
 *
 *
 *
 */
public class InterruptedExceptionThread {


    private static class  MyThread extends Thread{

        @Override
        public void run() {

            System.out.println("当前线程终端标示   isInterrupted  --------> "+isInterrupted());
            while (!isInterrupted()){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("线程阻塞后中断标示位  isInterrupted  ----------> "+isInterrupted());
                    /***
                     * do something
                     *
                     * 业务处理过程中可以用于释放一些资源 再手动标示中断标示位
                     */
                    interrupt();

                }
                System.out.println("线程阻塞结束后继续执行中断标示位     isInterrupted   -----------> "+isInterrupted());

            }
            System.out.println("线程最终中断标示位  isInterrupted -----------> "+isInterrupted());

        }

    }

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(2000);
        myThread.interrupt();

    }

}
