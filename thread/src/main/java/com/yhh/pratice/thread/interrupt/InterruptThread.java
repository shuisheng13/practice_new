package com.yhh.pratice.thread.interrupt;


/***
 * creator by yhh
 * interrupt 给线程打一个标示位  线程有不会立即终止，线程也有可能不会理会
 *
 * 需要配合isinterrupt来配合使用
 *
 * 线程终止后会释放线程所占资源
 *
 */
public class InterruptThread {

    private static class MyThread extends  Thread{

        @Override
        public void run() {

            while (true){
                System.out.println("i am not a interrput thread");
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(2000);
        myThread.interrupt();

    }


}
