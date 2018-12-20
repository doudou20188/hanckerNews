package com.com.cskaoyan.newsproject.service.Impl;

import com.com.cskaoyan.newsproject.bean.Comment;
import com.com.cskaoyan.newsproject.dao.CommentMapper;
import com.com.cskaoyan.newsproject.service.CommetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YangTao
 * @Date: 2018/12/19 0019
 */
@Service
public class CommetServiceImpl implements CommetService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> findCommentByNewsId(String newsID) {
        List<Comment> commentList=commentMapper.findCommentByNewsIdFromDB(newsID);
        return commentList;
    }

    @Override
    public boolean addComment(Comment comment) {
        boolean b =false;
        int insert = commentMapper.insert(comment);
        if (insert==1){
            b =true;
        }
        return b;
    }
}
