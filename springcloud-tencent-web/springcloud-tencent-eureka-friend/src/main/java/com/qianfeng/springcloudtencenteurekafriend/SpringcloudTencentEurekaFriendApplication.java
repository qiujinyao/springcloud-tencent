package com.qianfeng.springcloudtencenteurekafriend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication ( scanBasePackages = "com.qianfeng" )
@EnableEurekaClient
@EnableFeignClients ( basePackages = "com.qianfeng" )
@MapperScan ( "com.qianfeng.mapper" )
@EnableTransactionManagement
public class SpringcloudTencentEurekaFriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTencentEurekaFriendApplication.class, args);
    }

}
