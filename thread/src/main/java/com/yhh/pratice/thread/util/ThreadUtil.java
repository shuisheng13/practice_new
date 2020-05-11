package com.yhh.pratice.thread.util;

public class ThreadUtil {

    public static void printThreadNameStrar(){
        System.out.println("当前线程  ------>  "+Thread.currentThread().getName()  +"  start ");
    }

    public static void printThreadNameEnd(){
        System.out.println("当前线程  ------>  "+Thread.currentThread().getName()  +"  end ");
    }


    public static void SleepBySend(int n){

        try {
            Thread.sleep(n*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
