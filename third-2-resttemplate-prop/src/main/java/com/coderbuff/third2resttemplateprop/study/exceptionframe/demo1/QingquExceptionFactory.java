package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo1;


import com.coderbuff.third2resttemplateprop.study.exceptionframe.BaseException;

public class QingquExceptionFactory {

    /**
     * 基础自定义异常
     *
     * @param errorCodeEnums 错误消息
     * @return 基础异常类
     */
    public static QingquRunTimeException build(ErrorCodeEnums errorCodeEnums) throws BaseException {
        return new QingquRunTimeException(errorCodeEnums);
    }

    /**
     * 基础自定义异常
     *
     * @param errorCodeEnums 错误消息
     * @param errorMsgConcat 错误消息追加
     * @return 基础异常类
     */
    public static QingquRunTimeException build(ErrorCodeEnums errorCodeEnums, ErrorMsgConcat errorMsgConcat) throws BaseException {
        return new QingquRunTimeException(errorCodeEnums, errorMsgConcat);
    }

    /**
     * 动态构建异常信息
     *
     * @param code 错误code
     * @param message 错误消息
     * @return 基础异常类
     */
    public static QingquRunTimeException build(Integer code, String message) throws BaseException {
        return new QingquRunTimeException(code, message);
    }


}
