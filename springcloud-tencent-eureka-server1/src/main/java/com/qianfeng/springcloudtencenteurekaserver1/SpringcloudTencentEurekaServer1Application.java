package com.qianfeng.springcloudtencenteurekaserver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudTencentEurekaServer1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTencentEurekaServer1Application.class, args);
    }

}
