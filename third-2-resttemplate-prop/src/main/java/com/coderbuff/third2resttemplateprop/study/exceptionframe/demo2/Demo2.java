package com.coderbuff.third2resttemplateprop.study.exceptionframe.demo2;

import com.coderbuff.third2resttemplateprop.study.exceptionframe.demo1.QingquExceptionFactory;
import com.coderbuff.third2resttemplateprop.study.exceptionframe.demo1.QingquRunTimeException;
import com.coderbuff.third2resttemplateprop.study.exceptionframe.demo1.SystemErrorCodeEnum;

/**
 * @Author 喻可
 * @Date 2022/5/6 10:05
 */
public class Demo2 {

    public static void main(String[] args) {

        //测试1
        QingquRunTimeException2 exception = QingquExceptionFactory2.build(SystemErrorCodeEnum2.DELIVERY_HUNDRED_REAL_TIME_QUERY_ERROR);

        System.out.println(exception.toString());

        //测试2
        QingquRunTimeException2 build = QingquExceptionFactory2.build(SystemErrorCodeEnum2.PARAMETER_EMPTY,
                //这里设置需要回调的内容
                msg -> String.join("***", msg));

        System.out.println(build.toString());


    }


}
