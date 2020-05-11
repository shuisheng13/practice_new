package com.yhh.practice.redis.dislock.utils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

@Service
public class PushRedPacketUtils {


   @Autowired
   private Jedis jedis;

    @Resource(name="redislock")
    public  Lock lock;

    private static long MAX_RED_PACKET_COUNT =1500;
    private static CountDownLatch countDownLatch = new CountDownLatch(5);
    private static final String REDPACKET ="redPacket:";
    private static final String REDPACKET_POOLS = "redPacketPools";
    /***
     * 10个线程生产10000个红包
     */
    public  String pushRedPacket(){
        new Thread(new RedPacketThread("线程一",lock,jedis)).start();
        new Thread(new RedPacketThread("线程二",lock,jedis)).start();
        new Thread(new RedPacketThread("线程三",lock,jedis)).start();
        new Thread(new RedPacketThread("线程四",lock,jedis)).start();
        new Thread(new RedPacketThread("线程五",lock,jedis)).start();
        return "SUCCESS";
    }

    public static class RedPacketThread implements Runnable{

        private String name;
        private long currCon =0;
        private Lock lock;
        private Jedis jedis;

        public RedPacketThread(String name, Lock lock, Jedis jedis) {
            this.name = name;
            this.lock = lock;
            this.jedis = jedis;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
            if(countDownLatch.getCount()==0){
                System.out.println("线程全部准备就绪  开始抢红包");
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(MAX_RED_PACKET_COUNT>0){
                try{
                    lock.lock();
                    if(MAX_RED_PACKET_COUNT>0){
                        long id = jedis.incr("redPacketId");
                        String redPacketId = REDPACKET+id;
                        Map<String,String> map = new HashMap<String,String>();
                        map.put("rediPacketId",String.valueOf(id));
                        map.put("meoney",String.valueOf(10));
                        map.put("timer",String.valueOf(System.currentTimeMillis()));
                        String rt = jedis.hmset(redPacketId,map);
                        System.out.println(rt);
                        if("OK".equals(rt)){
                           jedis.lpush(REDPACKET_POOLS,redPacketId);
                           MAX_RED_PACKET_COUNT--;
                            currCon++;
                        }
                    }

                }finally {
                    lock.unlock();
                }
            }
            System.out.println(name+" 线程 生产了   "+currCon+" 个红包");

        }
    }

}
