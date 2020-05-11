package com.yhh.practice.redis.dislock.lock;


import com.yhh.practice.redis.dislock.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Service
public class redislock implements Lock {
    @Autowired
    private JedisConnectionFactory jedisConnectionFactoryl;

    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private static final String key = "dislock_001";
    @Override
    public void lock() {

        if(tryLock()){

            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock();

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        //获取JEDIS
        Jedis jedis  = (Jedis) jedisConnectionFactoryl.getConnection().getNativeConnection();
        //为每个线程分配一个UUID值  防止解锁时解了其他线程的锁
        String uuiud = UUID.randomUUID().toString();
        //nx 操作成功返回1 不成功返回0  px 设置过期时间  防止程序挂掉造成死锁
        String rt = jedis.set(key,uuiud,"NX","px",1000);
        if("OK".equals(rt)){
            //抢到锁后把UUID 存入线程变量
            threadLocal.set(uuiud);
            return true;
        }

        System.out.println("加锁失败");
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

        //通过lua 脚本把REDIS 锁到值和UUID 想比较  若是相等则表示是当前线程自己到锁  直接解锁

        String val = threadLocal.get();
        String script = FileUtils.getScript("unlock.lua");
        Jedis jedis = (Jedis) jedisConnectionFactoryl.getConnection().getNativeConnection();
        jedis.eval(script, Arrays.asList(key),Arrays.asList(val));

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
