package com.vky.aspect;

import com.vky.annotation.AutoFill;
import com.vky.context.BaseContext;
import com.vky.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class AutoFillAspect {
    //定义切入点(对哪些类的哪些方法进行拦截)
    @Pointcut("execution(* com.vky.dao.*.*(..)) && @annotation(com.vky.annotation.AutoFill)")
    public void autoFillPointcut(){}

    //通知
    /*
        采用前置通知，在通知中进行公共字段的填充
     */
    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint){
        //获取当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType operationType = autoFill.value();//获得数据库操作类型


        //获取到当前被拦截的方法上的参数-->实体对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }

        Object entity = args[0];


        //准备赋值所需的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();


        //根据当前不同的操作类型，为对应的属性赋值
        if (operationType == OperationType.INSERT) {
            //为4个公共字段赋值
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setCreateBy = entity.getClass().getDeclaredMethod("setCreateBy", Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateBy = entity.getClass().getDeclaredMethod("setUpdateBy", Long.class);

                //通过反射为对象赋值
                setCreateTime.invoke(entity, now);
                setCreateBy.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateBy.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (operationType == OperationType.UPDATE) {
            //为2个公共字段赋值
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateBy = entity.getClass().getDeclaredMethod("setUpdateBy", Long.class);

                //通过反射为对象赋值
                setUpdateTime.invoke(entity, now);
                setUpdateBy.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
