package com.qianfeng.springcloudtencentgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudTencentGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTencentGatewayApplication.class, args);
    }

}
