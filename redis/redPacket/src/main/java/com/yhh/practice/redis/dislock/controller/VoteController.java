package com.yhh.practice.redis.dislock.controller;


import com.yhh.practice.redis.dislock.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/***
 * 文章相关操作控制器
 */
@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
    @RequestMapping(value = "/pushActile")
    public String pushActile(String userId){
        String actileId = voteService.pushActile(userId);
        return actileId;
    }

    /***
     * 文章投票
     * @param userId
     * @param actileId
     * @return
     */
    @RequestMapping(value = "vote")
    public String vote(String userId,String actileId){
      String st =   voteService.voleActile(actileId,userId);
      return st;
    }

    @RequestMapping(value = "selectActile")
    public String selectActile(String actileId){
        String st =   voteService.selectActile(actileId);
        return st;
    }


}
