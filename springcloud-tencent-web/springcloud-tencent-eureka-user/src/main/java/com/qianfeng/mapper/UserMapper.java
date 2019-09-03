package com.qianfeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianfeng.entity.User;

import java.util.List;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/24
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> selectUserByUsername(String username);
}
