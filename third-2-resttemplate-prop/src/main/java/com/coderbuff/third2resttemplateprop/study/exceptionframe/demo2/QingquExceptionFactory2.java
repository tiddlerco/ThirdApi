package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo2;


import com.coderbuff.third2resttemplateprop.study.exceptionframe.BaseException;


public class QingquExceptionFactory2 {

    /**
     * 基础自定义异常
     *
     * @param errorCodeEnums 错误消息
     * @return 基础异常类
     */
    public static QingquRunTimeException2 build(ErrorCodeEnums2 errorCodeEnums) throws BaseException {
        return new QingquRunTimeException2(errorCodeEnums);
    }

    /**
     * 基础自定义异常
     *
     * @param errorCodeEnums 错误消息
     * @param errorMsgConcat 错误消息追加
     * @return 基础异常类
     */
    public static QingquRunTimeException2 build(ErrorCodeEnums2 errorCodeEnums, ErrorMsgConcat2 errorMsgConcat) throws BaseException {
        return new QingquRunTimeException2(errorCodeEnums, errorMsgConcat);
    }



}
