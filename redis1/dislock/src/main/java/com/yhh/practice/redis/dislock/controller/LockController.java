package com.yhh.practice.redis.dislock.controller;


import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/***
 * 模拟售票控制器
 */

@Controller
public class LockController {


    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    @Resource(name = "redislock")
    private Lock lock;
    /***
     * 20张票
     * 5个窗口一起出售
     * 总售票数不能超过20
     *
     * @return
     */
    public long save(){
        long con = 20;


        return 1;
    }

    public class salethread implements Runnable{

        private long con;
        private CountDownLatch countDownLatch;


        public salethread(long con, CountDownLatch countDownLatch) {
            this.con = con;
            this.countDownLatch = countDownLatch;
        }

        private long conunt =0;


        @Override
        public void run() {
            countDownLatch.countDown();
            if(countDownLatch.getCount()==0){
                //计数器用完
                System.out.println("已售票   "+conunt);
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while(con>0){
                lock.lock();
                try{
                    if(con>0){
                        con--;
                        conunt++;

                    }
                }finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(10);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("共抢票 "+conunt+"  张 ");
        }
    }



}
