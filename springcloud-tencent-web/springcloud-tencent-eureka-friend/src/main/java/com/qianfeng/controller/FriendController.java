package com.qianfeng.controller;

import com.qianfeng.entity.Friends;
import com.qianfeng.result.ResultBean;
import com.qianfeng.result.StatusCode;
import com.qianfeng.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/29
 */
@RestController
@RequestMapping ( "/friends" )
public class FriendController {
    @Autowired
    private IFriendService friendService;


    /**
     * 根据用户id查询好友列表
     *
     * @param uid
     * @return
     */
    @RequestMapping ( "/findmyfriends" )
    public ResultBean<List<Friends>> findmyfriendsByUserid(Integer uid) {
        List<Friends> friendsList = friendService.findmyfriendsByUserid(uid);
        if (friendsList.size() > 0) {
            return new ResultBean<>(StatusCode.SUCCESS_CODE, "", friendsList);
        }
        return new ResultBean<>(StatusCode.FRIENDSEMTY_CODE, "无好友", null);
    }
}
