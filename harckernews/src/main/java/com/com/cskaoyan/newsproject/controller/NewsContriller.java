package com.com.cskaoyan.newsproject.controller;

import com.com.cskaoyan.newsproject.bean.News;
import com.com.cskaoyan.newsproject.bean.User;
import com.com.cskaoyan.newsproject.service.NewsService;
import com.com.cskaoyan.newsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: YangTao
 * @Date: 2018/12/19 0019
 */
@Controller
public class NewsContriller {
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/user/addNews/")
    public Map addNews(HttpSession session, News news){
        HashMap hashMap = new HashMap();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        news.setUserId(userId);
        news.setLikeCount(0);
        news.setCreatedDate(new Date());
        boolean b =newsService.addNews(news);
        if (b){
            hashMap.put("code",0);
        }else {
            hashMap.put("code",1);

        }
        return  hashMap;
    }









}
