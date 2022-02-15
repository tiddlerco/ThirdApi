package com.coderbuff.third2resttemplateprop.study.mockTest;

import com.alibaba.testable.core.annotation.MockInvoke;
import com.coderbuff.third2resttemplateprop.Third2RestTemplatePropApplication;
import com.coderbuff.third2resttemplateprop.study.mockTest.entity.StorageMachineInfoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 喻可
 * @Date 2021/12/2 11:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Third2RestTemplatePropApplication.class)
public class DemoMockTest {

    @Autowired
    private DemoMock demoMock;

    public static class Mock {

        @MockInvoke(targetClass = DemoMock.class)
        private List<StorageMachineInfoDTO> mockMachineDate() {
            List<StorageMachineInfoDTO> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                StorageMachineInfoDTO dto = StorageMachineInfoDTO.builder()
                        .secondClassify("服务器")
                        .thirdClassify("高密机架服务器")
                        .factory("Huawei")
                        .type("Tecal RH2288H V" + i)
                        .standardModel("N" + i)
                        .model("N" + i)
                        .count(i)
                        .build();
                list.add(dto);
            }
            return list;
        }
    }

    /**
     * 注意三点
     * 1.DemoMock只能在调用getList时,mockMachineDate的mock方法才能生效
     * 2.被测试类和测试类的包名尽量保持一致  不一致要使用注解
     * 3.和spring测试容器一起使用 注意@Test注解的包名 org.junit.Test;
     *
     * @throws Exception
     */
    @Test
    public void test() {
        List<StorageMachineInfoDTO> date = demoMock.getList();
        System.out.println(date.size());
    }
}
