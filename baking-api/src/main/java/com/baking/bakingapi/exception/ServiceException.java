package com.baking.bakingapi.exception;


import com.baking.bakingapi.response.StatusCode;
import lombok.Getter;

//1.自定义业务异常类
public class ServiceException extends RuntimeException{
    @Getter
    private StatusCode statusCode;

    public ServiceException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}