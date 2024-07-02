package com.baking.bakingapi.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义枚举状态码的类：存放整个工程中所有的内部状态码和提示消息.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusCode {
    NOT_LOGIN(1000, "未登录"),
    LOGIN_SUCCESS(1001, "登录成功"),
    PASSWORD_ERROR(1002, "密码错误"),
    USERNAME_ERROR(1003, "用户名错误"),
    USERNAME_ALREADY_EXISTS(1004, "用户名被占用"),
    OPERATION_SUCCESS(2001, "操作成功"),
    OPERATION_FAILED(2002, "操作失败"),
    THROWABLE_ERROR(8000, "Throwable异常"),
    VALIDATED_ERROR(3001, "参数校验失败");
    private Integer code;
    private String msg;
}