package com.coderbuff.third2resttemplateprop;

import com.coderbuff.third2resttemplateprop.entity.JuheStockResponse;
import com.coderbuff.third2resttemplateprop.service.StockServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yulinfeng
 * @date 2019/12/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Third2RestTemplatePropApplication.class)
public class Third2RestTemplatePropApplicationTests {

    @Autowired
    private StockServiceImpl stockService;

    @Test
    public void test() {
        JuheStockResponse response = stockService.getStockByCode("sh601009");
        System.out.println(response);
    }
}
