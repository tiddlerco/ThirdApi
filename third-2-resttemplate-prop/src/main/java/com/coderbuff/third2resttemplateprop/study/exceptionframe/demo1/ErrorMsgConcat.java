package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo1;

/**
 * @author kam
 *
 * <p>
 * 异常消息连接
 * </p>
 */
@FunctionalInterface
public interface ErrorMsgConcat {

    /**
     * 这个方法本身就是一个回调逻辑
     * 获取本身 不做改变
     *
     * @return
     */
    static ErrorMsgConcat identity() {
        return msg -> msg;
    }

    /***
     * 错误追加消息
     *
     * @param msg
     * @return
     */
    String concat(String msg);
}
