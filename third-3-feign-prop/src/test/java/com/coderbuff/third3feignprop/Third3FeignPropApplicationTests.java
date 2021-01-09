package com.coderbuff.third3feignprop;

import com.coderbuff.third3feignprop.service.StockServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Third3FeignPropApplication.class)
public class Third3FeignPropApplicationTests {

    @Autowired
    private StockServiceImpl stockService;

    @Test
    public void test() {
        StockDTO sh600519 = stockService.getStockByCode("sh600519");
        System.out.println(sh600519);
    }

}
