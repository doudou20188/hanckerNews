package com.com.cskaoyan.newsproject.service;

import com.com.cskaoyan.newsproject.bean.Comment;

import java.util.List;

/**
 * @Auther: YangTao
 * @Date: 2018/12/19 0019
 */
public interface CommetService {
    List<Comment> findCommentByNewsId(String newsID);

    boolean addComment(Comment comment);
}
