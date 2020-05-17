package com.yhh.practice.principle;

/***
 * creator by yhh
 * 验证cpu开启过度线程
 */
public class Thread_01 {


    public static void main(String[] args) throws Exception{

        long start = System.currentTimeMillis();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                calc();
            }
        };

        int size = 1000;

        Thread[] threads =new  Thread[size];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    static void calc() {
        System.out.println("hello word");
    }


//    static void calc() {
//        int result = 0;
//        for (int m = 0; m < 10000; m++) {
//            for (int a = 0; a < 200; a++) {
//                result++;
//
//            }
//        }
//    }
}
