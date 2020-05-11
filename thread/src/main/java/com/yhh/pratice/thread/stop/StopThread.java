package com.yhh.pratice.thread.stop;

/****
 * creator by yhh
 *
 * stop方法属于暴力停止线程方法  会导致线程资源不会正常释放
 *
 * 不建议使用  但是确实能有效停止线程，如果业务允许可以考虑
 */

public class StopThread {


    private static class MyThread extends  Thread{

        @Override
        public void run() {
            super.run();

            while (true){
                System.out.println("i am not stoped thread");
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(2000);
        myThread.stop();

    }
}
