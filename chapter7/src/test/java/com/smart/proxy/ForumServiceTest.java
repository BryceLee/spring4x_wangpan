package com.smart.proxy;

import java.lang.reflect.Proxy;

import org.testng.annotations.Test;

public class ForumServiceTest {

    @Test
    public void test01() {
        // 业务类正常编码的测试
        ForumService forumService = new ForumServiceImpl();
        forumService.removeForum(10);
        forumService.removeTopic(1012);
    }

    @Test
    public void testJdkDynamicProxy() {
        ForumService target = new ForumServiceImpl();
        PerformanceHandler handler = new PerformanceHandler(target);
        ForumService proxy = (ForumService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
        proxy.removeForum(10);
        proxy.removeTopic(1012);
    }

    @Test
    public void testCglibDynamicProxy() {
        CglibProxy cglibProxy = new CglibProxy();
        ForumService forumService = (ForumService) cglibProxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1023);
    }

}
