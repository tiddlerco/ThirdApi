package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo2;


import com.coderbuff.third2resttemplateprop.study.exceptionframe.BaseException;


/**
 * 倾趣运行时异常
 */
public class QingquRunTimeException2 extends BaseException {

    /**
     * 异常code枚举
     */
    private ErrorCodeEnums2 errorCodeEnums;

    /**
     * 异常信息拼接
     */
    private ErrorMsgConcat2 errorMsgConcat;

    public QingquRunTimeException2(ErrorCodeEnums2 errorCodeEnums) {
        this(errorCodeEnums, ErrorMsgConcat2.identity());
    }



    public QingquRunTimeException2(ErrorCodeEnums2 errorCodeEnums, ErrorMsgConcat2 errorMsgConcat) {
        //这里是ErrorMsgConcat2的回调
        super(errorMsgConcat.concat(errorCodeEnums.getMsg()));
        setErrorCodeEnums2(errorCodeEnums);
        setErrorMsgConcat2(errorMsgConcat);
    }


    public ErrorCodeEnums2 getErrorCodeEnums2() {
        return errorCodeEnums;
    }

    public void setErrorCodeEnums2(ErrorCodeEnums2 errorCodeEnums) {
        this.errorCodeEnums = errorCodeEnums;
    }

    public ErrorMsgConcat2 getErrorMsgConcat2() {
        return this.errorMsgConcat;
    }

    public void setErrorMsgConcat2(ErrorMsgConcat2 errorMsgConcat) {
        this.errorMsgConcat = errorMsgConcat;
    }
}
