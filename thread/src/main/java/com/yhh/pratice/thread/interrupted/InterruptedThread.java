package com.yhh.pratice.thread.interrupted;

/****
 *
 * creator by yhh
 * interrupted JDK提供的静态方法
 *
 * 检测线程终止标示位
 *
 * 配合interrupt方法使用
 *
 * 不同的是interrupted 会把 isInterrupted 标示位改成false
 *
 *
 */
public class InterruptedThread {

    private static class MyThread extends  Thread{

        @Override
        public void run() {

            System.out.println("当前线程  isInterrupted --------->  "+isInterrupted());

            while (!Thread.interrupted()){
                System.out.println("当前线程正在执行中 -----------> " +isInterrupted());
            }
            System.out.println("线程已被终止 isInterrupted ---------------> "+isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(2000);
        myThread.interrupt();



    }
}
