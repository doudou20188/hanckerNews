package com.com.cskaoyan.newsproject.service.Imol;

import com.com.cskaoyan.newsproject.bean.User;
import com.com.cskaoyan.newsproject.dao.UserMapper;
import com.com.cskaoyan.newsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: YangTao
 * @Date: 2018/12/18 0018
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User findUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User theUser=userMapper.findUserFromDB(username,password);
        return theUser;
    }

    @Override
    public boolean register(User user) {
        boolean  b =false;

        int insert = userMapper.insertSelective(user);
        if (insert==1){
            b=true;
        }
        return b;
    }
}
