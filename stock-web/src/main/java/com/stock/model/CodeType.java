package com.stock.model;

/**
 * @Description: 返回的状态码
 * @Author: weiguodong
 * @Create: 2020/4/9 23:06
 */
public enum CodeType {
    Success(0),   //成功
    Failure(1),   //失败
    UserOrPasswordError(2),    //用户名或者密码错误
    UsernameNotFound(3),       //用户名不存在
    UserDisabled(4),           //您的账号已被冻结，请联系管理员！
    Invalidation(5),           //无效的检验
    ;

    public final int value;

    CodeType(int value) {
        this.value = value;
    }
}
