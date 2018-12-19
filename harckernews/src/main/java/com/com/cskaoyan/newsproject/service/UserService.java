package com.com.cskaoyan.newsproject.service;

import com.com.cskaoyan.newsproject.bean.User;

/**
 * @Auther: YangTao
 * @Date: 2018/12/18 0018
 */
public interface UserService {

    User findUser(User user);

    boolean register(User user);
}
