package com.yhh.practice.principle;

/***
 * createor by yhh
 *
 * 两个线程读取的数据位于同一个缓存行
 *
 * 验证缓存行执行效率
 */
public class Cache_01 {


    private static volatile  long [] arr = new long[2];

    public static void main(String[] args) throws Exception {


        Thread t1 = new Thread(()->{

            for (int i = 0; i <10000_0000_0 ; i++) {

                arr[0]=i;
            }
        });
        Thread t2 = new Thread(()->{

            for (int i = 0; i <10000_0000_0 ; i++) {
                arr[1] = i;
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
