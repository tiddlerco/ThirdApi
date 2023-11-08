package com.coderbuff.third2resttemplateprop.study.aopApplication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yuke
 * @Date 2023/11/7 17:22
 * @Description:
 */
@Aspect
@Component
public class DeferredAspect {

    private final List<Runnable> deferredTasks = new ArrayList<>();

    @Around("@annotation(Deferred)")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        // 把方法执行包装成Runnable
        Runnable task = () -> {
            try {
                pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        };

        // 将任务存储起来，等待后续执行
        deferredTasks.add(task);

        // 返回一个默认值或者相应类型的空值
        return getDefaultValue(pjp);
    }

    public void executeDeferredTasks() {
        deferredTasks.forEach(Runnable::run);
        deferredTasks.clear();
    }

    public void clearDeferredTasks() {
        deferredTasks.clear();
    }

    private Object getDefaultValue(ProceedingJoinPoint pjp) {
        // 根据方法返回类型提供默认值
        Class<?> returnType = ((MethodSignature) pjp.getSignature()).getReturnType();
        if (returnType.isPrimitive()) {
            if (returnType == boolean.class) {
                return false;
            } else if (returnType == byte.class || returnType == short.class ||
                    returnType == int.class || returnType == long.class ||
                    returnType == float.class || returnType == double.class ||
                    returnType == char.class) {
                return 0;
            }
        }
        return null;
    }
}

