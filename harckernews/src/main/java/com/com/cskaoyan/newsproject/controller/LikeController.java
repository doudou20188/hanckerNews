package com.com.cskaoyan.newsproject.controller;

import com.com.cskaoyan.newsproject.bean.User;
import com.com.cskaoyan.newsproject.utill.JedisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 点赞 点踩处理
 * @Auther: YangTao
 * @Date: 2018/12/20 0020
 */
@Controller
public class LikeController {
    @ResponseBody
    @RequestMapping("like")
    public Map like(HttpSession session, String newsId){
        HashMap hashMap = new HashMap();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        Jedis jedisFromPool = JedisUtils.getJedisFromPool();
        String  likeNewsId="likeNewId"+newsId;
        jedisFromPool.sadd(likeNewsId, userId.toString());
        Long likeCount = jedisFromPool.scard(likeNewsId);
        hashMap.put("msg",likeCount);
        hashMap.put("code",0);
        jedisFromPool.close();
        return hashMap;
    }
    @ResponseBody
    @RequestMapping("dislike")
    public Map dislike(HttpSession session,String newsId){
        HashMap hashMap = new HashMap();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        Jedis jedisFromPool = JedisUtils.getJedisFromPool();

        String dislikeNewId="dislikeNewId"+newsId;
        String  likeNewsId="likeNewId"+newsId;
        jedisFromPool.sadd(dislikeNewId, userId.toString());
        //取交集 返回 linkeNewsid 去除交集后的结      果int
        Long realCount = jedisFromPool.sdiffstore("realLikeSet" , likeNewsId, dislikeNewId);
        //删除likeNewsid 包含交集的部分
        Set<String> otherList = jedisFromPool.sinter(likeNewsId, dislikeNewId);
        for (String ss:
             otherList) {
            //删除交集部分
            Long srem = jedisFromPool.srem(likeNewsId, ss);
            Long srem2 = jedisFromPool.srem(dislikeNewId, ss);
        }
        hashMap.put("msg",realCount);
        hashMap.put("code",0);
        jedisFromPool.close();
        return  hashMap;
    }






}
