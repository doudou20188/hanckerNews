package com.com.cskaoyan.newsproject.service.Impl;

import com.com.cskaoyan.newsproject.bean.News;
import com.com.cskaoyan.newsproject.dao.NewsMapper;
import com.com.cskaoyan.newsproject.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YangTao
 * @Date: 2018/12/19 0019
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    @Override
    public List<News> findAllNews() {
        List<News> newsList=newsMapper.findAllNewsFromDB();
        return newsList;
    }

    @Override
    public boolean addNews(News news) {
        boolean b =false;
        int insert = newsMapper.insert(news);
        if (insert==1){
            b=true;
        }

        return  b ;
    }

    @Override
    public News findNewsById(String newsID) {
        News news = newsMapper.selectByPrimaryKey(Integer.parseInt(newsID));
        return news;
    }
}
