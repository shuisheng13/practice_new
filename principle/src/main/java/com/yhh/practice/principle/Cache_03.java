//package com.yhh.practice.principle;
//
//import sun.misc.Contended;
//
//public class Cache_03{
//
//    @Contended
//    private static volatile long  arrs = 0;
//
//
//    public static void main(String[] args) throws Exception{
//
//
//        Thread t1= new Thread(()->{
//            for (int i = 0; i < 10000_0000; i++) {
//                arrs = i;
//            }
//        });
//
//        final long start = System.nanoTime();
//
//        t1.start();
//        t1.join();
//        System.out.println((System.nanoTime()-start)/100_0000);
//    }
//
//}
