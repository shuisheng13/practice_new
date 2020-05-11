package com.yhh.pratice.thread.waitAndNotify;

import com.yhh.pratice.thread.util.ThreadUtil;
import sun.awt.windows.ThemeReader;

public class ConsumerProviderTest {


    public static void main(String[] args) throws InterruptedException {

       Bore bore  = new Bore();
       Comsumer comsumer = new Comsumer();
       comsumer.setBore(bore);
       Provider provider = new Provider();
       provider.setBore(bore);
       Thread thread1 = new Thread(comsumer);
       thread1.setName("生产者线程");
        Thread thread2 = new Thread(provider);
        thread2.setName("消费者线程");
        thread1.start();
        ThreadUtil.SleepBySend(2);
        thread2.start();

        Thread.sleep(200000);
    }
}
