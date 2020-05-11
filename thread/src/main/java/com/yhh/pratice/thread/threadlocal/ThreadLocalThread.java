package com.yhh.pratice.thread.threadlocal;

import javax.swing.text.StyledEditorKit;

/***
 *
 * cretor by yhh
 * 验证多个线程访问修改自己的threadLocal变量是否会产生脏数据
 *
 * 结论： 每个线程只会修改到自己到threadLocal变量 不会有影响
 *
 * threadLocal 会给自己到线程生成一个变量副本
 *
 */
public class ThreadLocalThread {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    private static class MyThread extends Thread{

        private Integer i;

        public void setI(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {

            Integer con = threadLocal.get();
            con +=i;
            threadLocal.set(con);
            System.out.println("当前线程  "+Thread.currentThread().getName()
            +"  con --------->  "+threadLocal.get());

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <1000 ; i++) {
            MyThread my = new MyThread();
            my.setI(i);
            my.start();
        }
    }

}
