package com.yhh.practice.principle;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableCallable;

/***
 * creator by yhh
 * 验证纤程效率
 *
 */
public class Thread_02 {


    public static void main(String[] args) throws Exception{

        long start = System.currentTimeMillis();
        int size = 1000000;

        Fiber<Void>[] fibers = new Fiber[size];

        for (int i = 0; i < fibers.length; i++) {

            fibers[i]= new Fiber<Void>(new SuspendableCallable<Void>() {
                public Void run() throws SuspendExecution, InterruptedException {
                    calc();
                    return null;
                }
            });

        }

        for (int j = 0; j <fibers.length ; j++) {
            fibers[j].start();

        }
        for (int j = 0; j <fibers.length ; j++) {
            fibers[j].join();

        }


        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }



    static void calc() {
        int result = 0;
        for (int m = 0; m < 10000; m++) {
            for (int i = 0; i < 200; i++){
                result++;
            }
        }
    }
}
