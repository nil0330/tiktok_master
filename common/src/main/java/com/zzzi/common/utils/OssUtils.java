package com.zzzi.common.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:tfy
 * @Date:2024/04/21/16:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssUtils {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
