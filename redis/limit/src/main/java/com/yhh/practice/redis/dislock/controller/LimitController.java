package com.yhh.practice.redis.dislock.controller;


import com.yhh.practice.redis.dislock.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 限流控制器
 */
@RestController
public class LimitController {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @RequestMapping(value = "/limit")
    public String limit(long timer,long limit,String ip){


        Jedis jedis = getJedis();
        String script = FileUtils.test();
        List<String> list = new ArrayList<String>();
        list.add(String.valueOf(limit));
        list.add(String.valueOf(timer));
        Object obj =  jedis.eval(script,Arrays.asList(ip),list);
        System.out.println(obj);
        return "";
    }

    public Jedis getJedis(){

        Jedis jedis = (Jedis) jedisConnectionFactory.getConnection().getNativeConnection();
        return jedis;
    }

}
