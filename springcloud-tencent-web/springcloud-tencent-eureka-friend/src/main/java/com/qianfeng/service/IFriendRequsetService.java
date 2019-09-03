package com.qianfeng.service;

import com.qianfeng.entity.FriendsRequest;
import com.qianfeng.result.ResultBean;

import java.util.List;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/28
 */
public interface IFriendRequsetService {
    ResultBean insertRequest(FriendsRequest friendsRequest);

    ResultBean<List<FriendsRequest>> findFriendRequestByUserId(Integer toid);

    ResultBean updateQuestStatus(FriendsRequest request);
}
