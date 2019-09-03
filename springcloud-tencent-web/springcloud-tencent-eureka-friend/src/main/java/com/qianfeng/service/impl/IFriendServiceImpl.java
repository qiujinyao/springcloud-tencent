package com.qianfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianfeng.api.IUserService;
import com.qianfeng.entity.Friends;
import com.qianfeng.entity.User;
import com.qianfeng.mapper.FriendMapper;
import com.qianfeng.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/28
 */
@Service
public class IFriendServiceImpl implements IFriendService {
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private IUserService userService;

    @Override
    public List<Friends> findmyfriendsByUserid(Integer uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        List<Friends> list = friendMapper.selectList(queryWrapper);
        for (Friends friend : list) {
            User user = userService.searchUserById(friend.getFid());
            friend.setUser(user);
        }
        return list;
    }
}
