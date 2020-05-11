package com.yhh.practice.redis.dislock.service;

import java.util.List;

public interface VoteService {

    //发布文章
    String pushActile(String userId);
    //给某个文章投票
    String voleActile(String actile,String userId);
    // 按文章ID 查找文章详情
    String selectActile(String actileId);
    //按时间和分数查找文章列表
    List<String> selectActiles(long score,long timers);
}
