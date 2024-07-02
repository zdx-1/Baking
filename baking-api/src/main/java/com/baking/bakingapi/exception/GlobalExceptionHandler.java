package com.baking.bakingapi.exception;


import com.baking.bakingapi.response.JsonResult;
import com.baking.bakingapi.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * RestControllerAdvice组合注解：等价于 ControllerAdvice注解 + ResponseBody注解;
 *   1.将此类标识为全局异常处理器的类;
 *   2.一旦控制器中抛出异常,则框架会先检查有没有定义全局异常处理器;
 *     2.1 如果未定义全局异常处理器:走SpringMVC默认的异常处理机制[对用户端不太友好];
 *     2.2 如果定义了全局异常处理器,则会在该全局异常处理的类中找对应的异常处理方法;
 * 异常处理策略：
 *   第1步：控制器抛出异常时,首先找到全局异常处理器的类[@RestControllerAdvice注解标识的类];
 *   第2步：在类中找到对应的异常处理方法进行处理;
 *   第3步：如果未定义该异常处理方法,则会找该异常父类异常的处理方法;
 *   第4步：如果未定义父类异常的处理方法,则使用Spring MVC默认的异常处理机制[不友好].
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public JsonResult doHandleServiceException(ServiceException ex){
        //在IDEA控制台打印异常日志,如:RuntimeException:用户名已被占用
        log.error("RuntimeException: " + ex.getStatusCode().getMsg());
        //返回错误状态码
        return new JsonResult(ex.getStatusCode());
    }
    /**
     * 第1个异常处理方法:IllegalArgumentException
     * ExceptionHandler注解：表示此方法为异常处理方法;
     * @param ex 异常对象
     * @return JsonResult
     */
    @ExceptionHandler
    public JsonResult doHandleIllegalArgumentException(IllegalArgumentException ex){
        //获取异常提示消息: ex.getMessage() [微博ID值无效]
        String message = ex.getMessage();
        log.error("IllegalArgumentException: " + message);

        return new JsonResult(StatusCode.OPERATION_FAILED, message);
    }

    /**
     * 第2个异常处理方法:RuntimeException
     * @param ex 异常处理对象
     * @return JsonResult
     */
    @ExceptionHandler
    public JsonResult doHandleRuntimeException(RuntimeException ex){
        String message = ex.getMessage();
        log.error("RuntimeException: " + message);

        return new JsonResult(StatusCode.OPERATION_FAILED, message);
    }

    /**
     * 第3个异常处理方法:MethodArgumentNotValidException
     * ------------专门处理POJO类型校验抛出的异常----------------
     * 1.Spring Validation框架抛出;
     * 2.校验POJO类属性时,参数未满足要求.
     */
    @ExceptionHandler
    public JsonResult doHandleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        //Validation中,验证POJO类型参数获取提示消息使用: ex.getFieldError().getDefaultMessage() 方法.
        String message = ex.getFieldError().getDefaultMessage();
        log.error("MethodArgumentNotValidException: " + message);

        return new JsonResult(StatusCode.VALIDATED_ERROR, message);
    }

    /**
     * 第4个异常处理方法:ConstraintViolationException
     * ------------专门处理声明参数方式验证框架校验抛出的异常----------------
     * 1.Spring Validation框架抛出;
     * 2.声明参数方式接收数据校验时,参数未满足要求.
     */
    @ExceptionHandler
    public JsonResult doHandleConstraintViolationException(ConstraintViolationException ex){
        // selectById.id: 微博ID必须在200-500之间  ---->  微博ID必须在200-500之间
        String message = ex.getMessage().split(":")[1].trim();
        log.error("ConstraintViolationException: " + message);

        return new JsonResult(StatusCode.VALIDATED_ERROR, message);
    }

    /**
     * 第5个异常处理方法：Throwable
     * 1.能处理控制器抛出的所有的异常;
     * 2.一般最后再加此方法.
     */
    @ExceptionHandler
    public JsonResult doHandleThrowable(Throwable ex){
        String message = ex.getMessage();
        log.error("Throwable异常: " + message);

        return new JsonResult(StatusCode.THROWABLE_ERROR, message);
    }
}
