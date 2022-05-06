package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo2;

import java.util.List;

/**
 * @author kam
 *
 * <p>
 * 异常消息连接
 * </p>
 */
@FunctionalInterface
public interface ErrorMsgConcat2 {

    /**
     * 这个方法本身就是一个回调
     * 获取本身 不做改变
     *
     * @return
     */
    static ErrorMsgConcat2 identity() {
        return msg -> String.join("===", msg);
    }

    /***
     * 错误追加消息
     *
     * @param msg
     * @return
     */
    String concat(List<String> msg);
}
