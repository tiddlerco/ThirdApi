package com.coderbuff.third2resttemplateprop.study.mapstruct;

import com.coderbuff.third2resttemplateprop.study.mydesign.strategy.UpdateItemOperation;
import com.coderbuff.third2resttemplateprop.study.spring.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author 喻可
 * @Date 2022/4/24 10:34
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapStructTest {

    @Resource
    private UserMapping userMapping;

    @Test
    public void test(){
        UpdateItemOperation updateItemOperation = SpringContextUtil.getBean("updateItemOperation",UpdateItemOperation.class);

        updateItemOperation.templateMethod();
    }

    @Test
    public void testDomain2DTO() {
        User user = new User()
                .setId(1L)
                .setUsername("zhangsan")
                .setSex(1)
                .setPassword("abc123")
                .setCreateTime(LocalDateTime.now())
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig("[{\"field1\":\"Test Field1\",\"field2\":500}]");
        UserVo userVo = userMapping.sourceToTarget(user);
        log.info("User: {}", user);
        //User: User(id=1, username=zhangsan, password=abc123, sex=1, birthday=1999-09-27, createTime=2020-01-17T17:46:20.316, config=[{"field1":"Test Field1","field2":500}])
        log.info("UserVo: {}", userVo);
        //UserVo: UserVo(id=1, username=zhangsan, gender=1, birthday=1999-09-27, createTime=2020-01-17 17:46:20, config=[UserVo.UserConfig(field1=Test Field1, field2=500)])
    }

    @Test
    public void testDTO2Domain() {
        UserVo.UserConfig userConfig = new UserVo.UserConfig();
        userConfig.setField1("Test Field1");
        userConfig.setField2(500);

        UserVo userVo = new UserVo()
                .setId(1L)
                .setUsername("zhangsan")
                .setGender(2)
                .setPassword("abc123")
                .setCreateTime("2020-01-18 15:32:54")
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig(Collections.singletonList(userConfig));
        User user = userMapping.targetToSource(userVo);
        log.info("UserVo: {}", userVo);
        //UserVo: UserVo(id=1, username=zhangsan, gender=2, birthday=1999-09-27, createTime=2020-01-18 15:32:54, config=[UserVo.UserConfig(field1=Test Field1, field2=500)])
        log.info("User: {}", user);
        //User: User(id=1, username=zhangsan, password=null, sex=2, birthday=1999-09-27, createTime=2020-01-18T15:32:54, config=[{"field1":"Test Field1","field2":500}])
    }


    @Test
    public void testDomainList2DTOList() {
        User user = new User()
                .setId(1L)
                .setUsername("zhangsan")
                .setSex(1)
                .setPassword("abc123")
                .setCreateTime(LocalDateTime.now())
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig("[{\"field1\":\"Test Field1\",\"field2\":500}]");

        List<User> users = new ArrayList<>();
        users.add(user);

        List<UserVo> userVo = userMapping.sourceListToTargetList(users);
        log.info("[User: {}]", user);
        //User: User(id=1, username=zhangsan, password=abc123, sex=1, birthday=1999-09-27, createTime=2020-01-17T17:46:20.316, config=[{"field1":"Test Field1","field2":500}])
        log.info("[UserVo: {}]", userVo);
        //UserVo: UserVo(id=1, username=zhangsan, gender=1, birthday=1999-09-27, createTime=2020-01-17 17:46:20, config=[UserVo.UserConfig(field1=Test Field1, field2=500)])
    }

    @Test
    public void testDTOList2DomainList() {
        UserVo.UserConfig userConfig = new UserVo.UserConfig();
        userConfig.setField1("Test Field1");
        userConfig.setField2(500);

        UserVo userVo = new UserVo()
                .setId(1L)
                .setUsername("zhangsan")
                .setGender(2)
                .setPassword("abc123")
                .setCreateTime("2020-01-18 15:32:54")
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig(Collections.singletonList(userConfig));

        List<UserVo> userVos = new ArrayList<>();
        userVos.add(userVo);

        List<User> users = userMapping.targetListToSourceList(userVos);
        log.info("UserVo: [{}]", userVos);
        //UserVo: UserVo(id=1, username=zhangsan, gender=2, birthday=1999-09-27, createTime=2020-01-18 15:32:54, config=[UserVo.UserConfig(field1=Test Field1, field2=500)])
        log.info("User: [{}]", users);
        //User: User(id=1, username=zhangsan, password=null, sex=2, birthday=1999-09-27, createTime=2020-01-18T15:32:54, config=[{"field1":"Test Field1","field2":500}])
    }


}
