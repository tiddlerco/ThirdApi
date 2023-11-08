package com.coderbuff.third2resttemplateprop.study.aopApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yuke
 * @Date 2023/11/7 17:22
 * @Description:
 */
@Service
public class BusinessService {

    @Autowired
    private DeferredAspect deferredAspect;


    @Autowired
    private DeferredOperateService deferredOperateService;



    public void executeBusinessLogic() {
        try {
            deferredOperateService.updateRedis();

            deferredOperateService.sendMQMessage();

            System.out.println("操作数据库");
            deferredAspect.executeDeferredTasks();
        } catch (Exception e) {
            // 如果出现异常，清除所有延迟的任务
            deferredAspect.clearDeferredTasks();
            throw e;
        }
    }


}

