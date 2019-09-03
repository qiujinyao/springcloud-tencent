package com.qianfeng.api;

import com.qianfeng.api.impl.IUserServiceImpl;
import com.qianfeng.entity.User;
import com.qianfeng.result.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/24
 */
@FeignClient(value = "EUREKA-USER",fallback = IUserServiceImpl.class)
@Component
public interface IUserService {

    @PostMapping ("/user/updataphoto")
    ResultBean updataUserPhoto(@RequestParam("image") String image, @RequestParam("thumimage") String thumimage, @RequestParam("userId") Integer userId);

    @RequestMapping ("/user/searchUserById")
    User searchUserById(@RequestParam("userId") Integer userId);
}
