package com.qianfeng.service;

import com.qianfeng.entity.Friends;

import java.util.List;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/28
 */
public interface IFriendService {
    List<Friends> findmyfriendsByUserid(Integer uid);
}
