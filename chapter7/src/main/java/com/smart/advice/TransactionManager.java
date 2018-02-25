package com.smart.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class TransactionManager implements ThrowsAdvice {

    /**
     * 方法名必须是 afterThrowing
     * 前三个参数 Method method, Object[] args, Object target 是可选的（要么三个都提供，要么都不提供）
     * 最后一个参数是 Throwable 或其子类
     *
     * afterThrowing 方法可以被重载（即定义多个 afterThrowing 方法），Spring 会自动选择最适合的方法来调用
     * @param method
     * @param args
     * @param target
     * @param ex
     * @throws Throwable
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
        System.out.println("-----------");
        System.out.println("method:" + method.getName());
        System.out.println("抛出异常:" + ex.getMessage());
        System.out.println("成功回滚事务。");
    }
}
