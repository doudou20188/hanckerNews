package com.com.cskaoyan.newsproject.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Auther: YangTao
 * @Date: 2018/12/19 0019
 */
public interface FileUpLoadService {
    String uploadFile(MultipartFile file) throws IOException;
}
