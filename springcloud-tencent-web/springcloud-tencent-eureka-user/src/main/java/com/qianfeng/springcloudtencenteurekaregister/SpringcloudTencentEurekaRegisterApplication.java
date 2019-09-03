package com.qianfeng.springcloudtencenteurekaregister;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication ( scanBasePackages = "com.qianfeng" )
@EnableEurekaClient
@RefreshScope
@Import ( FdfsClientConfig.class )
@MapperScan ( basePackages = "com.qianfeng.mapper" )

public class SpringcloudTencentEurekaRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTencentEurekaRegisterApplication.class, args);
    }

}
