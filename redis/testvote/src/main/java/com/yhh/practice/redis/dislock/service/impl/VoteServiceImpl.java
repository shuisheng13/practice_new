package com.yhh.practice.redis.dislock.service.impl;

import com.yhh.practice.redis.dislock.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {


    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;


    private final static String ACTILE="actile:";
    private final static String USERID ="userId:";
    private final static String VOTE ="vote:";
    private final static String SCOREINFO="score:info";
    private final static long SCORE=100;
    private final static String DATEACTILE = "data:actile";

    @Override
    public String pushActile(String userId) {

        Jedis jedis = getJedis();
        long id = jedis.incr("actile");
        String actileId = ACTILE+id;
        Map<String,String> map = new HashMap<String,String>();
        map.put("actileId",actileId);
        map.put("url","baidu.com");
        map.put("content","这是一篇关于测试的文章");
        map.put("timer",String.valueOf(System.currentTimeMillis()));
        map.put("userId",userId);
        String rt = jedis.hmset(actileId, map);
        if("OK".equals(jedis.hmset(actileId,map))){
            //创建文章成功后给自己给文章投1票
            String userI=USERID+userId;
            String voteKey = new StringBuffer().append(VOTE).append(id).toString();
            if(1==jedis.sadd(voteKey,userI)){
                System.out.println(userI+"创建文章时投票成功"+"  文章ID ： "+actileId );
            }
            if(1==jedis.zadd(SCOREINFO,SCORE,actileId)){
                System.out.println("文章 : "+actileId+"   创建时新增积分 ： "+SCORE+"  成功");
            }
            if(1==jedis.zadd(DATEACTILE,System.currentTimeMillis(),actileId)){
                System.out.println("文章 ： "+actileId+"  新增时  插入 文章日期表成功");
            }
            return actileId;
        }
        return "";
    }

    @Override
    public String voleActile(String actile, String userId) {

        /***
         * 给文章投票
         * 判断用户是否给文章已经投票过了
         * 判断文章投票时间是否已经结束
         */
        Jedis jedis = getJedis();
        String voteKey = new StringBuffer().append(VOTE).append(actile).toString();
        String userI=USERID+userId;
        String actileId = ACTILE+actile;
        long curr = System.currentTimeMillis();
        if(jedis.hget(actileId,"timer")!=null&&curr-Long.valueOf(jedis.hget(actileId,"timer"))<10000*60){
            if(1==jedis.sadd(voteKey,userI)){
                //给文章投票新增积分
                jedis.zincrby(SCOREINFO,SCORE,actileId);
                //给文章插入时间
                jedis.zadd(DATEACTILE,System.currentTimeMillis(),actileId);
                return "投票成功 ";

            }
            System.out.println("用户  "+userI+"  已经给文章 "+actile+" 投过票了，不允许重复投票");
            return "false";
        }
        System.out.println("文章 ： "+actileId+"    已经超过投票期限");
        return "false";
    }

    @Override
    public String selectActile(String actileId) {
        Jedis jedis =getJedis();
        String actileI = ACTILE+actileId;

        Map<String,String> map = jedis.hgetAll(actileI);
        return map.toString();
    }

    @Override
    public List<String> selectActiles(long score, long timers) {
        return null;
    }

    public Jedis getJedis(){

        Jedis jedis = (Jedis) jedisConnectionFactory.getConnection().getNativeConnection();
        return jedis;

    }
}
