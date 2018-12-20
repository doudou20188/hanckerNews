package com.com.cskaoyan.newsproject.service.Impl;

import com.aliyun.oss.OSSClient;
import com.com.cskaoyan.newsproject.service.FileUpLoadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Auther: YangTao
 * @Date: 2018/12/19 0019
 */
@Service
public class FileUpLoadServiceImpl implements FileUpLoadService {
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAIlFgD7Hozqftv";
        String accessKeySecret = "LMSUvIKmOP2p7iU1KZYKsBrbsfIhzt";

// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        String bukectname="newsproject";
// 上传文件流。
        String key= UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        ossClient.putObject(bukectname, key, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        return "https://"+bukectname +".oss-cn-shenzhen.aliyuncs.com/" + key;
    }
}
