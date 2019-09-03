package com.qianfeng.controller;

import com.qianfeng.entity.User;
import com.qianfeng.result.ResultBean;
import com.qianfeng.result.StatusCode;
import com.qianfeng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/24
 */
@RestController
@RequestMapping ( "/user" )
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 登录验证
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping ( "/checkLogin" )
    public ResultBean<User> checkLogin(String userName, String password) {
        ResultBean<User> resultBean = userService.checkLogin(userName, password);
        return resultBean;
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @RequestMapping ( "/register" )
    public ResultBean<User> registerUser(User user) {
        ResultBean<User> resultBean = userService.insertUser(user);
        return resultBean;
    }

    /**
     * 更换头像
     *
     * @param image
     * @param thumimage
     * @param userId
     * @return
     */
    @PostMapping ( "/updataphoto" )
    public ResultBean updataUserPhoto(@RequestParam ( "image" ) String image, @RequestParam ( "thumimage" ) String thumimage, @RequestParam ( "userId" ) Integer userId) {
        ResultBean resultBean = userService.updataUserPhoto(image, thumimage, userId);
        return resultBean;
    }

    /**
     * 按用户名查找用户
     *
     * @param username
     * @return
     */
    @RequestMapping ( "/searchfriend" )
    public ResultBean<List<User>> searchfriendByUsername(String username) {
        List<User> userList = userService.searchfriendByUsername(username);
        if (userList.size() > 0) {

            return new ResultBean<>(StatusCode.SUCCESS_CODE, "", userList);
        }
        return new ResultBean<>(StatusCode.USER_EMPTY, "用户不存在！", null);
    }

    /**
     * 按用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @RequestMapping ( "/searchUserById" )
    public User searchUserById(Integer userId) {
        return userService.searchUserById(userId);
    }
}
