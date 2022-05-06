package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo2;

import java.util.List;

public interface ErrorCode2 {

    /**
     * 返回异常码
     * @return
     */
    Integer getCode();
   /**
     * 返回异常消息
     * @return
     */
    List<String> getMsg();

}
