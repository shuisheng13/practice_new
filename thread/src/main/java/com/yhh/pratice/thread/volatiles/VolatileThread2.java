package com.yhh.pratice.thread.volatiles;


/***
 * creator by yhh
 *
 * 验证volatile 是否能保证写一致性
 *
 * 多个线程去写同一个volatile
 *
 * 结论：不能保证写一致性
 */
public class VolatileThread2 {


    private static volatile Integer num= 0;

    private static Object object = new Object();


    private static class MyThread1 extends Thread{


        @Override
        public void run() {

            synchronized (object){
                for (int i = 0; i <1000 ; i++) {
                    num++;
                }
                System.out.println("当前线程  "+Thread.currentThread().getName()
                        +"   num ----------->  "+num);
            }




        }
    }

    public static void main(String[] args) {

        MyThread1 my1 = new MyThread1();
        my1.setName("thread1");
        MyThread1 my2 = new MyThread1();
        my2.setName("thread2");
        my1.start();
        my2.start();

    }



}
