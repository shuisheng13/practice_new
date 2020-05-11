package com.yhh.pratice.thread.synchronize;


/****
 *
 * creator by yhh
 *
 * 验证Integer 做为锁对象  进行加减操作是否能保证原子性
 *
 * 结论：加减操作时不能保证原子操作 Integer加减操作会生成新的对象
 *
 *
 */
public class SynchronizedThread4 {

    private static Integer num = 0;

    private static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("当前线程  "+Thread.currentThread().getName()
            +"  num hashCode "+System.identityHashCode(num));
            synchronized (num){
                num++;
                System.out.println("当前线程  "+Thread.currentThread().getName()
                        +"  num hashCode "+System.identityHashCode(num));
            }
            System.out.println("当前线程  "+Thread.currentThread().getName()
                    +"  num ----------> "+num);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            MyThread my = new MyThread();
            my.setName("thread "+i);
            my.start();
        }
    }
}
