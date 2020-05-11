package com.yhh.practice.redis.dislock.controller;


import com.yhh.practice.redis.dislock.utils.PushRedPacketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 抢红包控制器
 */
@RestController
public class RedPacketController {

    @Autowired
    private PushRedPacketUtils pushRedPacketUtils;
    /***
     * 多线程生产红包1000个红包
     * @return
     */
    @RequestMapping("/pushRedPacket")
    public String pushRedPacket(){
        return pushRedPacketUtils.pushRedPacket();
    }


//    public String lootRedPacket(){
//
//
//
//    }

}

