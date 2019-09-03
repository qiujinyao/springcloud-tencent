package com.qianfeng.springcloudtencenteurekaregister;

import com.qianfeng.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith ( SpringRunner.class )
@SpringBootTest
@MapperScan ( basePackages = "com.qianfeng.mapper" )
public class SpringcloudTencentEurekaRegisterApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
       /* User user = userMapper.selectByName("邱金耀");
        System.out.println(user);*/
    }

}
