package com.com.cskaoyan.newsproject.controller;

import com.com.cskaoyan.newsproject.bean.Comment;
import com.com.cskaoyan.newsproject.bean.News;
import com.com.cskaoyan.newsproject.bean.User;
import com.com.cskaoyan.newsproject.service.CommetService;
import com.com.cskaoyan.newsproject.service.NewsService;
import com.com.cskaoyan.newsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 留言控制器
 * @Auther: YangTao
 * @Date: 2018/12/19 0019
 */
@Controller
public class CommentContriller {
    @Autowired
    CommetService commetService;
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;

    @RequestMapping("news/{newsID}")
    public String showNewsByID(@PathVariable("newsID") String newsID, Model model){
        System.out.println(newsID);
        model.addAttribute("contextPath","");
        News newsById=newsService.findNewsById(newsID);
        model.addAttribute("news",newsById);
        //推荐商品对应的本用户
        Integer userId = newsById.getUserId();
        User owner = userService.findUserById(userId);
        model.addAttribute("owner",owner);
        model.addAttribute("like",20);//暂且未知like 字段作用

        //获取该news 对应的所有评论信息
        ArrayList comments = new ArrayList();
        List<Comment> commentList= commetService.findCommentByNewsId(newsID);
        for (Comment comment:
             commentList) {
            HashMap hashMap = new HashMap();
            Integer commentUserId = comment.getCommentUserId();
            User userByComment = userService.findUserById(commentUserId);
            hashMap.put("user",userByComment);
            hashMap.put("comment",comment);
            comments.add(hashMap);
        }

        model.addAttribute("comments",comments);
        return "detail";
    }

    @RequestMapping("addComment")
    public String addComment(Comment comment, String commit, HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        Integer newsId = comment.getNewsId();
        comment.setCommentUserId(userId);
        comment.setCreatedDate(new Date());
        boolean b =commetService.addComment(comment);
        return "news/"+newsId.toString();
    }







}
