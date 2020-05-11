package com.yhh.practice.redis.dislock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/***
 * 模拟售票控制器
 */

@RestController
public class LockController {


    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    @Resource(name = "redislock")
    private Lock lock;

    long count = 0;
    /***
     * 20张票
     * 5个窗口一起出售
     * 总售票数不能超过20
     *
     * @return
     */
    @RequestMapping(value = "/sale")
    public long save(long count){
        this.count = count;
        new Thread(new salethread(countDownLatch,"窗口1")).start();
        new Thread(new salethread(countDownLatch,"窗口2")).start();
        new Thread(new salethread(countDownLatch,"窗口3")).start();
        new Thread(new salethread(countDownLatch,"窗口4")).start();
        new Thread(new salethread(countDownLatch,"窗口5")).start();
        new Thread(new salethread(countDownLatch,"窗口6")).start();
        new Thread(new salethread(countDownLatch,"窗口7")).start();
        new Thread(new salethread(countDownLatch,"窗口8")).start();
        new Thread(new salethread(countDownLatch,"窗口9")).start();
        new Thread(new salethread(countDownLatch,"窗口10")).start();


        return 1;
    }

    public class salethread implements Runnable{


        private CountDownLatch countDownLatch;
        private String name;


        public salethread(CountDownLatch countDownLatch, String name) {
            this.countDownLatch = countDownLatch;
            this.name = name;
        }

        private long conunt =0;


        @Override
        public void run() {
            countDownLatch.countDown();
            if(countDownLatch.getCount()==0){
                //计数器用完
                System.out.println("----------所有窗口已准备到位 开始售票------------------------------");
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while(count>0){
                lock.lock();
                System.out.println(name+"  窗口已抢到锁....................");
                try{
                    if(count>0){
                        count--;
                        conunt++;

                    }else{
                        System.out.println(name+"  票已出售完  抢到锁但未抢到票");
                    }
                }finally {
                    lock.unlock();
                    System.out.println(name +"   解锁成功");

                }
                try {
                    Thread.sleep(50);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name+"  共抢票  "+conunt+"  张 ");
        }
    }



}
