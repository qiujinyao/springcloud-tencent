package com.qianfeng.springcloudtencenteurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudTencentEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTencentEurekaServerApplication.class, args);
    }

}
