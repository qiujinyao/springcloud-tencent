package com.qianfeng.api.impl;

import com.qianfeng.api.IUserService;

import com.qianfeng.entity.User;
import com.qianfeng.result.ResultBean;
import com.qianfeng.result.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/24
 */
@Component
public class IUserServiceImpl implements IUserService {
    //更换头像的降级方法

    @Override
    public ResultBean updataUserPhoto(String image, String thumimage, Integer userId) {
        return new ResultBean(StatusCode.FAIL_CODE, "服务器未响应", null);

    }

    @Override
    public User searchUserById(Integer userId) {
        return null;
    }
}

