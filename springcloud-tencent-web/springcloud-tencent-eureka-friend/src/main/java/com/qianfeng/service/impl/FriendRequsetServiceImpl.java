package com.qianfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianfeng.api.IUserService;
import com.qianfeng.entity.Friends;
import com.qianfeng.entity.FriendsRequest;
import com.qianfeng.entity.User;
import com.qianfeng.mapper.FriendMapper;
import com.qianfeng.mapper.FriendRequstMapper;
import com.qianfeng.result.ResultBean;
import com.qianfeng.result.StatusCode;
import com.qianfeng.service.IFriendRequsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/28
 */
@Service
public class FriendRequsetServiceImpl implements IFriendRequsetService {

    @Autowired
    FriendRequstMapper friendRequstMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private IUserService userService;

    //插入申请数据至数据库
    @Override
    public ResultBean insertRequest(FriendsRequest friendsRequest) {
        //先判断是否已经发送过验证请求
        Map<String, Object> queryMap = new HashMap<>(2);
        queryMap.put("fromid", friendsRequest.getFromid());
        queryMap.put("toid", friendsRequest.getToid());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.allEq(queryMap);
        FriendsRequest friendsReq = friendRequstMapper.selectOne(queryWrapper);
        //判断是否非空
        try {
            if (friendsReq == null) {
                //直接添加至数据库
                int result = friendRequstMapper.insert(friendsRequest);
                if (result > 0) {
                    return new ResultBean<>(StatusCode.SUCCESS_CODE, "", "");
                }
                return new ResultBean<>(StatusCode.FAIL_CODE, "服务器繁忙，请稍后再试", null);
            } else if (friendsReq.getStatus() != 2) {//已发送但是还未审核或已审核  2表示拒绝
                return new ResultBean<>(StatusCode.HAVESENDREQUSET_CODE, "好友申请已发送,待对方验证通过,勿重复提交", null);
            }
            //已经发送过的申请 当时被拒绝 可以重新发起请求更新状态即可
            friendsReq.setStatus(0);
            int result = friendRequstMapper.updateById(friendsReq);
            if (result > 0) {
                return new ResultBean<>(StatusCode.SUCCESS_CODE, "", "");
            }
        } catch (Exception e) {
            return new ResultBean<>(StatusCode.ERROR_CODE, "服务器未响应", null);
        }
        return new ResultBean<>(StatusCode.ERROR_CODE, "服务器未响应", null);

    }

    @Override
    public ResultBean<List<FriendsRequest>> findFriendRequestByUserId(Integer toid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("toid", toid);
        queryWrapper.orderByDesc("create_time");
        List<FriendsRequest> requestList = friendRequstMapper.selectList(queryWrapper);
        if (requestList != null && requestList.size() > 0) {
            for (FriendsRequest request : requestList) {
                User user = userService.searchUserById(request.getFromid());
                user.setPassword(null);
                request.setUser(user);
            }
            return new ResultBean<>(StatusCode.SUCCESS_CODE, "", requestList);
        }
        return new ResultBean<>(StatusCode.NOFRIENDREQUEST_CODE, "暂无好友申请", null);
    }

    @Override
    @Transactional
    public ResultBean updateQuestStatus(FriendsRequest request) {

        try {
            int result = friendRequstMapper.updateById(request);
            if (result > 0 && request.getStatus() == 1) {//t通过好友认证，往好友关系表添加数据    l两次添加
                Friends friends = new Friends();
                friends.setUid(request.getToid());
                friends.setFid(request.getFromid());
                friendMapper.insert(friends);
                Friends friendsr = new Friends();
                friendsr.setUid(request.getFromid());
                friendsr.setFid(request.getToid());
                friendMapper.insert(friendsr);
            } else {
                return new ResultBean<>(StatusCode.AUTHORFRIENDFAIL_CODE, "好友认证失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean<>(StatusCode.ERROR_CODE, "服务器繁忙，稍后再试！", null);
        }
        return new ResultBean<>(StatusCode.SUCCESS_CODE, "已添加对方为好友", null);
    }
}
