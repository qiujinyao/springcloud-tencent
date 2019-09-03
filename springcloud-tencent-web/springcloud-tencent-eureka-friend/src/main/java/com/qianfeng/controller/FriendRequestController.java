package com.qianfeng.controller;

import com.qianfeng.entity.FriendsRequest;
import com.qianfeng.result.ResultBean;
import com.qianfeng.service.IFriendRequsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/28
 */
@RestController
@RequestMapping ( "/friend" )
public class FriendRequestController {

    @Autowired
    private IFriendRequsetService requsetService;

    /**
     * 添加好友申请
     *
     * @param friendsRequest
     * @return
     */
    @RequestMapping ( "/request" )
    public ResultBean friendRequest(FriendsRequest friendsRequest) {
        ResultBean resultBean = requsetService.insertRequest(friendsRequest);
        return resultBean;
    }

    /**
     * 根据用户id查询好友接受到的好友申请
     *
     * @param toid
     * @return
     */
    @RequestMapping ( "/findrequest" )
    public ResultBean<List<FriendsRequest>> findFriendRequest(Integer toid) {
        ResultBean<List<FriendsRequest>> resultBean =
                requsetService.findFriendRequestByUserId(toid);
        return resultBean;
    }

    @RequestMapping ( "/handerrequest" )
    @Transactional
    public ResultBean handerrequest(FriendsRequest request) {
        ResultBean resultBean = requsetService.updateQuestStatus(request);
        return resultBean;
    }
}
