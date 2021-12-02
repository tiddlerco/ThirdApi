package com.coderbuff.third2resttemplateprop.study.mockTest;

import com.alibaba.testable.core.annotation.MockMethod;
import com.coderbuff.third2resttemplateprop.Third2RestTemplatePropApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static com.alibaba.testable.core.matcher.InvokeVerifier.verify;

/**
 * @Author 喻可
 * @Date 2021/12/2 10:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Third2RestTemplatePropApplication.class)
public class NameServiceTest {

    @Autowired
    private NameService nameService;

    public static class Mock {

        @MockMethod(targetClass = NameService.class)
        private String bulidName(String name) {

            return name + "mock";
        }

    }

    /**
     * 注意三点
     * 1.nameService只能在调用getName时,bulidName的mock方法才能生效
     * 2.被测试类和测试类的包名尽量保持一致  不一致要使用注解
     * 3.和spring测试容器一起使用 注意@Test注解的包名
     *
     * @throws Exception
     */
    @Test
    public void should_set_mock_context() throws Exception {
        String name = nameService.getName("喻可");
        verify(name).equals("喻可mock");
        System.out.println(name);
    }

}
