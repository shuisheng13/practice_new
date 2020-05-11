package com.yhh.pratice.thread.join;

/****
 * creator by yhh
 *
 * 线程让步   让线程并行变成串行
 *
 * a 调用b的join方法   执行顺序 b  -----> a
 *
 * a 调用b join ,b调用 c的join   执行顺序  c ------> b  -------> a
 *
 */

public class joinThread {


    private static class  A implements Runnable{

        private Thread thread;

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public void run() {
            System.out.println("我是线程  ---------> "+Thread.currentThread().getName());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if(null!=thread){
                    thread.join();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程 --------->   "+Thread.currentThread().getName()+"  已经执行完成");

        }
    }

    private static class  B implements Runnable {

        private Thread threadl;

        public void setThreadl(Thread threadl) {
            this.threadl = threadl;
        }

        public void run() {

            try {

                System.out.println("我是线程  ------>  " + Thread.currentThread().getName());
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null != threadl) {
                try {
                    threadl.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("线程 --------->   " + Thread.currentThread().getName() + "  已经执行完成");

        }
    }

        private static class C implements Runnable {
            public void run() {

                try {

                    System.out.println("我是线程  ------>  " + Thread.currentThread().getName());
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程 --------->   " + Thread.currentThread().getName() + "  已经执行完成");

            }
        }


        public static void main(String[] args) throws InterruptedException {

            A a = new A();
            Thread a1 = new Thread(a);
            B b = new B();
            Thread b1 = new Thread(b);
            C c = new C();
            Thread c1 = new Thread(c);
            a1.setName("A");
            a.setThread(b1);
            c1.setName("C");
            b.setThreadl(c1);
            b1.setName("B");
            a1.start();
            b1.start();
            c1.start();

        }

    }

