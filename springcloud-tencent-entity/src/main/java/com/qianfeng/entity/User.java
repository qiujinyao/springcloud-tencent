package com.qianfeng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String password;
    private String nickName;
    private String header;
    private String headerCrm;
    private String email;
    private String phone;
    private String pinyin;
    private String qrcode;
    private Integer status=0;
    private Date createTime=new Date();


}
