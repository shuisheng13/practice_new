package com.yhh.pratice.thread.isInterrupt;

/***
 * creator by yhh
 *
 * isInterrupted 用于检测线程终端标示位  配合interrupt使用
 *
 * 终止线程会正常线程所占用资源
 *
 *
 */
public class IsInterruptedThread {

    private static class  MyThread extends Thread{
        @Override
        public void run() {

            System.out.println("当前线程 isterrupted状态  -------->  "+isInterrupted());

            while (!isInterrupted()){
                System.out.println("当前线程还在执行中  isInterrupted  -------> "+isInterrupted());

            }
            System.out.println("当前线程已终止  isInterrupted  -------> "+isInterrupted());

        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(2000);
        myThread.interrupt();

    }
}
