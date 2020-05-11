package com.yhh.practice.principle;

/***
 * creator by yhh
 * 两个线程读取的数据不位于同一缓存行
 *
 *
 */
public class Cache_02 {


    private static volatile long [] arrs = new long[16];


    public static void main(String[] args) throws Exception{


        Thread t1= new Thread(()->{
            for (int i = 0; i < 10000_0000_0; i++) {
                arrs[0] = i;
            }
        });

        Thread t2= new Thread(()->{
            for (int i = 0; i < 10000_0000_0; i++) {
                arrs[8] = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime()-start)/100_0000);
    }
}


