package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo1;


import com.coderbuff.third2resttemplateprop.study.exceptionframe.BaseException;

/**
 * 倾趣运行时异常
 */
public class QingquRunTimeException extends BaseException {

    /**
     * 异常code枚举
     */
    private ErrorCodeEnums errorCodeEnums;

    /**
     * 异常信息拼接
     */
    private ErrorMsgConcat errorMsgConcat;

    public QingquRunTimeException(ErrorCodeEnums errorCodeEnums) {
        this(errorCodeEnums, ErrorMsgConcat.identity());
    }

    public QingquRunTimeException(Integer code, String message) {
        this(code, message, ErrorMsgConcat.identity());
    }


    public QingquRunTimeException(ErrorCodeEnums errorCodeEnums, ErrorMsgConcat errorMsgConcat) {
        //这里是ErrorMsgConcat的回调
        super(errorMsgConcat.concat(errorCodeEnums.getMsg()));
        setErrorCodeEnums(errorCodeEnums);
        setErrorMsgConcat(errorMsgConcat);
    }

    public QingquRunTimeException(Integer code, String message, ErrorMsgConcat errorMsgConcat) {
        super(errorMsgConcat.concat(message));
        setErrorCodeEnums(new ErrorCodeEnums() {
            @Override
            public Integer getCode() {
                return code;
            }

            @Override
            public String getMsg() {
                return message;
            }
        });
        setErrorMsgConcat(errorMsgConcat);
    }

    public ErrorCodeEnums getErrorCodeEnums() {
        return errorCodeEnums;
    }

    public void setErrorCodeEnums(ErrorCodeEnums errorCodeEnums) {
        this.errorCodeEnums = errorCodeEnums;
    }

    public ErrorMsgConcat getErrorMsgConcat() {
        return this.errorMsgConcat;
    }

    public void setErrorMsgConcat(ErrorMsgConcat errorMsgConcat) {
        this.errorMsgConcat = errorMsgConcat;
    }
}
