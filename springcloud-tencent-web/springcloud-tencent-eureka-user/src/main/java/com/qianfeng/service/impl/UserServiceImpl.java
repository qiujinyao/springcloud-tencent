package com.qianfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qianfeng.entity.User;
import com.qianfeng.mapper.UserMapper;
import com.qianfeng.utils.PinYinCharUtils;
import com.qianfeng.result.ResultBean;
import com.qianfeng.result.StatusCode;
import com.qianfeng.service.IUserService;
import com.qianfeng.utils.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/27
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FastFileStorageClient client;
    @Value ( "${images.path}" )
    private String imagepath;

    @Override
    public ResultBean<User> checkLogin(String userName, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);
        try {
            User user = userMapper.selectOne(queryWrapper);
            if (user != null && user.getPassword().equals(password)) {
                user.setPassword(null);
                return new ResultBean<>(StatusCode.SUCCESS_CODE, null, user);
            } else {
                return new ResultBean<>(StatusCode.FAIL_CODE, "用户名密码错误!", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean<>(StatusCode.ERROR_CODE, "服务器繁忙，请稍后重试", null);
        }
    }

    @Override
    public ResultBean<User> insertUser(User user) {

        //TODO 获得拼音
        String pinYin = PinYinCharUtils.getPinYin(user.getNickName());
        user.setPinyin(pinYin);
        //TODO  获得二维码
        File file = null;
        try {
            File tempFile = File.createTempFile("qrcode_" + user.getUserName(), "png");
            QRCodeUtils.createQRCode(tempFile, "tencent:" + user.getUserName());
            //上传到fast服务器
            StorePath storePath = client.uploadFile(new FileInputStream(tempFile), tempFile.length(), "png", null);
            String qrcodepath = imagepath + storePath.getFullPath();
            user.setQrcode(qrcodepath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                file.delete();
            }
        }
        try {
            int resutl = userMapper.insert(user);
            if (resutl > 0) {
                return new ResultBean<>(StatusCode.SUCCESS_CODE, "注册成功！", null);
            } else {
                return new ResultBean<>(StatusCode.ERROR_CODE, "服务器繁忙，请稍后重试！", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean<>(StatusCode.ERROR_CODE, "服务器繁忙，请稍后重试！", null);
        }
    }

    @Override
    public ResultBean updataUserPhoto(String image, String thumimage, Integer userId) {
        try {
            User user = new User();
            user.setId(userId);
            user.setHeader(image);
            user.setHeaderCrm(thumimage);
            Map<String, String> imageMap = new HashMap<>(2);
            imageMap.put("image", image);
            imageMap.put("thumimage", thumimage);
            int result = userMapper.updateById(user);
            if (result > 0) {
                return new ResultBean(StatusCode.SUCCESS_CODE, "上传成功", imageMap);
            } else {
                return new ResultBean(StatusCode.FAIL_CODE, "服务器繁忙，稍后再试", null);
            }
        } catch (Exception e) {
            return new ResultBean(StatusCode.ERROR_CODE, "服务器响应失败，稍后再试", null);
        }
    }

    @Override
    public List<User> searchfriendByUsername(String username) {
        List<User> userList = userMapper.selectUserByUsername(username);
        return userList;
    }

    @Override
    public User searchUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

}
