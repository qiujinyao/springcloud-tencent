package com.qianfeng.springcloudtencenteurekaserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudTencentEurekaServer2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTencentEurekaServer2Application.class, args);
    }

}
