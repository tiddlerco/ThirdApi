package com.redis;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * 股票传输对象
 * @author yulinfeng
 * @date 2019/12/26
 */
@Setter
@Getter
@ToString
public class StockDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7801703039670841L;
    /**
     * 股票名称
     */
    private String name;

    private String price;

    //省略其它字段
}
