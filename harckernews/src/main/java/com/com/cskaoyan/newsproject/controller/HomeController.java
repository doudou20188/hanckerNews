package com.com.cskaoyan.newsproject.controller;

import com.com.cskaoyan.newsproject.bean.News;
import com.com.cskaoyan.newsproject.bean.User;
import com.com.cskaoyan.newsproject.service.NewsService;
import com.com.cskaoyan.newsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: YangTao
 * @Date: 2018/12/18 0018
 */
@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    NewsService newsService;

    @RequestMapping("/")
    public String trunToHome(Model model,HttpSession session){
        model.addAttribute("contextPath","");
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        //获取所有的new 和user
        List<News>newsList= newsService.findAllNews();
        ArrayList vos = new ArrayList();
        for (News news:
             newsList) {
            HashMap hashMap = new HashMap();
            Integer userId = news.getUserId();
            User userById=userService.findUserById(userId);
            hashMap.put("user",userById);
            hashMap.put("news",news);
            //暂且未知Like 的作用
            hashMap.put("like",20);


            vos.add(hashMap);
        }
        model.addAttribute("vos",vos);
        if (user==null){

            model.addAttribute("pop",0); //检测是否弹框登陆
        }else {
            model.addAttribute("pop",1);

        }
        return "home";
    }
    /**
     * 登陆
     */
    @ResponseBody
    @RequestMapping("login")
    public Map login(Model model,User user,String rember,HttpSession session){
        HashMap hashMap = new HashMap();

        //检验数据库是否有该用户 返回user
        User theUser=userService.findUser(user);
        model.addAttribute("contextPath","");
        if (theUser!=null){
            //{"msg":"成功","code":0}
            hashMap.put("msg","注册成功");
            hashMap.put("code",0);
            session.setAttribute("user",theUser);
        }else {

            hashMap.put("msg","用户已经存在!");
            hashMap.put("code",1);
        }

        return hashMap;

    }

    /**
     * 注册
     * @param model
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("reg")
    public Map register(Model model, User user, HttpSession session){
        HashMap hashMap = new HashMap();
        //将用户加入数据库操作 返回user
        boolean b =userService.register(user);

        /*User userReg = new User();
        userReg.setId(2);
        userReg.setUsername("taotao");
        userReg.setPassword("123456");*/


        model.addAttribute("contextPath","");

        if (b){
            //{"msg":"注册成功","code":0}
            User theUser = userService.findUser(user);
            session.setAttribute("user",theUser);
            hashMap.put("msg","注册成功");
            hashMap.put("code",0);
        }else {
            //{"code":1,"msgname":"用户名已经被注册"}
            hashMap.put("msg","用户名已经被注册");
            hashMap.put("code",1);
        }

        return hashMap;
    }
    /**
     * 注销
     *
     */
    @RequestMapping("logout")
    public String logout(Model model,HttpSession session){
        session.invalidate();
        //重定向
        return "redirect:/";
    }





}
