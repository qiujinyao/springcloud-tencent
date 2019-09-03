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
public class Friends {
    @TableId ( type = IdType.INPUT )
    private Integer uid;
    @TableId(type = IdType.INPUT)
    private Integer fid;

    private Date createTime=new Date();

    private Integer status=0;

    private String remark;
    @TableField(exist = false)
    private  User user;

}