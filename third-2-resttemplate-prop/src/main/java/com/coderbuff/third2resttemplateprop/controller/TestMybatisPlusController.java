package com.coderbuff.third2resttemplateprop.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.coderbuff.third2resttemplateprop.common.page.UserPage;
import com.coderbuff.third2resttemplateprop.entity.User;
import com.coderbuff.third2resttemplateprop.service.UserService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 喻可
 * @Date 2022/4/27 13:55
 */
@RestController
public class TestMybatisPlusController {


    @Resource
    private UserService userService;

    @Resource
    private ThreadPoolTaskExecutor executor;


    /**
     * 添加
     */
    @GetMapping("/add")
    public void testAdd() {
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setName("我是" + i + "号");
            user.setAge(i);
            userService.save(user);
        }
    }

    /**
     * 测试多线程下使用唯一索引添加数据
     */
    @GetMapping("/addWithUniqueIndex")
    public void testAddWithUniqueIndex() {
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                User user = new User();
                user.setName("yyk");
                user.setAge(27);
                try {
                    userService.save(user);
                } catch (Exception e) {
                    System.out.println("数据已经存在");
                }

            });
        }
    }


    /**
     * 更新带乐观锁
     */
    @GetMapping("/updateById")
    public void testUpdateById() {

        for (int i = 0; i < 150; i++) {
            executor.execute(() -> {
                boolean isSuccess;
                do {
                    User user = userService.getById(801L);
                    Integer age = user.getAge();
                    user.setAge(age + 1);
                    user.setVersion(user.getVersion());
                    isSuccess = userService.updateById(user);
                } while (!isSuccess);

            });
        }

    }

    /**
     * 更新带Wrapper
     * 更新所有年龄为2的人的名字为233
     */
    @GetMapping("/updateByWrapper")
    @Transactional(rollbackFor = Exception.class)
    public void testUpdateByWrapper() {

        try {
            User user = userService.getById(801L);
            Integer age = user.getAge();
            user.setAge(age + 1);
            boolean b1 = userService.updateById(user);

            User user2 = userService.getById(802L);
            user2.setName("888");
            boolean b = userService.updateById(user2);

            if (b) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }

    /**
     * 更新带Wrapper+Entity
     */
    @GetMapping("/updateByWrapperWithEntity")
    public void testUpdateByWrapperWithEntity() {

        User user = new User();
        user.setName("乐佳加").setAge(18);

        boolean update = userService.update(user, Wrappers.<User>lambdaUpdate().eq(User::getAge, 1)
        );
    }


    /**
     * 测试普通查询
     */
    @GetMapping("/testSelect")
    public void testSelectById() {

        User byId = userService.getById(701L);

        //只显示查询select后面的字段,其他为null
        User user1 = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getId, 701L).select(User::getId, User::getName, User::getAge));
        //显示全部字段
        User user2 = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getId, 701L));

        List<User> list = userService.list();

        List<User> useList = userService.list(Wrappers.<User>lambdaQuery().eq(User::getName, "乐佳加"));


        System.out.println(byId);
        System.out.println(user1);
        System.out.println(user2);

        System.out.println(list.size());

        System.out.println(useList);

    }

    /**
     * 测试删除
     */
    @GetMapping("/testDelete")
    public void testDelete() {
        //逻辑删除
        userService.removeById(703L);
        //条件删除
        userService.remove(Wrappers.<User>lambdaQuery().eq(User::getName, "我是4号"));
    }


    /**
     * 测试分页查询
     */
    @GetMapping("/testPageQuery")
    public void testPageQuery() {

        UserPage selectPage = new UserPage(1, 5);

        //SELECT COUNT(*) AS total FROM `user` WHERE deleted = 0
        //SELECT id,`name`,age,version,deleted,create_time,update_time FROM `user` WHERE deleted=0 LIMIT ?
        UserPage userPage = userService.page(selectPage);

        System.out.println(userPage.getRecords().size());

        System.out.println(JSON.toJSONString(userPage));

    }


    /**
     * 测试分页查询
     * 结合wrapper
     */
    @GetMapping("/testPageQueryWithWrapper")
    public void testPageQueryWithWrapper() {

        UserPage selectPage = new UserPage(1, 500);

        //SELECT COUNT(*) AS total FROM `user` WHERE deleted = 0
        //SELECT id,`name`,age,version,deleted,create_time,update_time FROM `user` WHERE deleted=0 AND (`name` = ?) LIMIT ?
        UserPage userPage = userService.page(selectPage, Wrappers.emptyWrapper());
        //判断是否还有下一页
        userPage.setHasNext(userPage.hasNext());

        System.out.println(userPage.getRecords().size());

        System.out.println(JSON.toJSONString(userPage));

    }

    /**
     * 测试分页排序查询
     */
    @GetMapping("/testPageOrderQuery")
    public void testPageOrderQuery() {

        UserPage selectPage = new UserPage(1, 5);
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("age");
        orderItem.setAsc(false);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setColumn("create_time");
        orderItem2.setAsc(true);

        List<OrderItem> list = new ArrayList<>();
        list.add(orderItem);
        list.add(orderItem2);

        selectPage.addOrder(list);

        //SELECT COUNT(*) AS total FROM `user` WHERE deleted = 0
        //SELECT id, `name`, age, version, deleted, create_time, update_time FROM `user` WHERE deleted = 0 ORDER BY age DESC, create_time ASC LIMIT ?
        UserPage userPage = userService.page(selectPage);

        System.out.println(userPage.getRecords().size());

        System.out.println(JSON.toJSONString(userPage));

    }


}
