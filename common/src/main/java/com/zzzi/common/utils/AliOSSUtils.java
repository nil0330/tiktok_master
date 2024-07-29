package com.zzzi.common.utils;

/**
 * @Author:tfy
 * @Date:2024/04/21/16:18
 */

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 阿里云 oss 工具类
 */
@Slf4j
@Component
public class AliOSSUtils {
    @Autowired
    private OssUtils ossUtils;
    private String endpoint = " ";
    private String accessKeyId = " ";
    private String accessKeySecret = " ";
    private String bucketName = " ";

    public String upload(MultipartFile file,String suffix) throws IOException {
        //上传文件到oss
//        OSS ossClient = new OSSClientBuilder().build(ossUtils.getEndpoint(),ossUtils.getAccessKeyId(),ossUtils.getAccessKeySecret());
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        try{
            //获取上传文件的输入流
            InputStream inputStream = file.getInputStream();

            //避免文件覆盖
            String originalFilename = file.getOriginalFilename();
            // 生成唯一文件名，当前时间 +UUID+ 文件类型
            //指定文件保存的路径为存储桶下面的tiktok/文件夹下
            String choice = suffix.equals("_cover.jpg") ? "cover/" : "video/";
            String fileName = "tiktok/" + choice + LocalDateTime.now() + UUID.randomUUID() + suffix;


            ossClient.putObject(bucketName,fileName,inputStream);

            //文件访问路径
//            String url = ossUtils.getEndpoint().split("//")[0] + "//" + ossUtils.getBucketName() + "." + ossUtils.getEndpoint().split("//")[1] + "/" + fileName;
            String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

            return url;
        } catch (Exception e){
            throw new RuntimeException("上传文件失败");
        } finally {
            //关闭客户端
            ossClient.shutdown();
        }
    }


}
