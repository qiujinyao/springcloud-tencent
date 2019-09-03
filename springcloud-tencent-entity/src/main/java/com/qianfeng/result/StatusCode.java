package com.qianfeng.result;

/**
 * @author qiujinyao
 * @version 1.0
 * @Date 2019/8/27
 */
public interface StatusCode {
     String  SUCCESS_CODE="0000";//成功状态码
     String  FAIL_CODE="1000";//失败状态码
     String  ERROR_CODE="1001";//异常状态码

     //用户服务状态码
     String USER_EMPTY="1002";//用户不存在
     //已经发送过了好友申请
     String HAVESENDREQUSET_CODE="1003";
     //无好友申请
     String NOFRIENDREQUEST_CODE="1004";
     //好友验证失败
     String AUTHORFRIENDFAIL_CODE="1005";
     //无好友列表
     String FRIENDSEMTY_CODE="1006";

}
