package com.qianfeng.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qianfeng.api.IUserService;
import com.qianfeng.result.ResultBean;
import com.qianfeng.result.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/24
 */
@RestController
@RequestMapping ( "/fileupload" )
public class UploadController {
    @Autowired
    private FastFileStorageClient client;

    @Autowired
    private IUserService userService;
    //读取配置文件的文件路径
    @Value ( "${images.path}" )
    private String imagePath;

    @PostMapping ( "/upload" )
    public ResultBean<Map> upload(MultipartFile file, Integer userId) {
        //获得文件名称
        try {
            System.out.println(file.getSize());
            System.out.println(file.getInputStream());
            String filename = file.getOriginalFilename();
            //取出后缀
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            //定义上传后的完整引用的文件名称 http://*******
            StringBuilder newfileName = new StringBuilder();
            StorePath storePath = client.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
                    suffix, null);
            String fullPath = storePath.getFullPath();
            //原始大小
            newfileName.append(imagePath).append(fullPath);
            //缩略图
            String thumimage = newfileName.toString().replaceAll("." + suffix, "_100x100." + suffix);
            ResultBean resultBean = userService.updataUserPhoto(newfileName.toString(), thumimage, userId);
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean<>(StatusCode.FAIL_CODE, "服务器未响应，请稍后再试！", null);
        }

    }
}
