package com.qianfeng.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean<T> implements Serializable {
    private String statusCode;
    private String msginfo;
    private T data;
}
