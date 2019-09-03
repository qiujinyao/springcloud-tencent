package com.qianfeng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsRequest {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer fromid;

    private Integer toid;

    private Date createTime=new Date();

    private Integer status=0;

    private String remark;

    //排除该字段不匹配数据库
    @TableField(exist = false)
    private  User user;
    }