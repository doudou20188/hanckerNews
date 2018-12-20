package com.com.cskaoyan.newsproject.controller;

import com.com.cskaoyan.newsproject.service.FileUpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器，上传地址阿里云
 * @Auther: YangTao
 * @Date: 2018/12/19 0019
 */
@Controller
public class FileUploadController {
    @Autowired
    FileUpLoadService fileUpLoadService;

    @ResponseBody
    @RequestMapping("uploadImage")
    public Map upLoadImage(Model model, MultipartFile file){
        HashMap hashMap = new HashMap();
        //{"msg":"http://192.168.2.100:8080/wangdaonews/image?name=da64272a40584ec5ac079821e32f43e6.jpg","code":0}
        String msg=null;
        try {
             msg = fileUpLoadService.uploadFile(file);
            hashMap.put("msg",msg);
            hashMap.put("code",0);
        } catch (IOException e) {
            hashMap.put("msg",msg);
            hashMap.put("code",1);
            e.printStackTrace();
        }

        return hashMap;
    }




}
