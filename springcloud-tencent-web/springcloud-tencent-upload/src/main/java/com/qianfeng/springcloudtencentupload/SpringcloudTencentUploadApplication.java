package com.qianfeng.springcloudtencentupload;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication ( scanBasePackages = "com.qianfeng" )
@Import ( FdfsClientConfig.class )
@EnableEurekaClient
@EnableFeignClients ( basePackages = "com.qianfeng.api" )
public class SpringcloudTencentUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTencentUploadApplication.class, args);
    }

}
