package com.com.cskaoyan.newsproject.controller;

import com.com.cskaoyan.newsproject.bean.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: YangTao
 * @Date: 2018/12/18 0018
 */
@RestController
public class Mycontroller {

    @RequestMapping("theuserDemo")
    public Map theDemo(){
        HashMap hashMap = new HashMap();
        hashMap.put("username","yangtao");
        hashMap.put("password","123456o");
        return hashMap;

    }




}
