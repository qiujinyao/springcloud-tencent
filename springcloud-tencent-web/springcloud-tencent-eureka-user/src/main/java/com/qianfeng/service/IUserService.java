package com.qianfeng.service;

import com.qianfeng.entity.User;
import com.qianfeng.result.ResultBean;

import java.util.List;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/27
 */
public interface IUserService {
    ResultBean<User> checkLogin(String userName, String password);

    ResultBean<User> insertUser(User user);

    ResultBean updataUserPhoto(String image, String thumimage, Integer userId);

    List<User> searchfriendByUsername(String username);

    User searchUserById(Integer userId);
}
