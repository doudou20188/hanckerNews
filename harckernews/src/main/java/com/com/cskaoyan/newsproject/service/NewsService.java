package com.com.cskaoyan.newsproject.service;

import com.com.cskaoyan.newsproject.bean.News;

import java.util.List;

/**
 * @Auther: YangTao
 * @Date: 2018/12/19 0019
 */
public interface NewsService {
    List<News> findAllNews();

    boolean addNews(News news);

    News findNewsById(String newsID);
}
