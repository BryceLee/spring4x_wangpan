package com.smart.aspectj.example;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.testng.annotations.Test;

import com.smart.NaiveWaiter;
import com.smart.Waiter;

public class AspectJProxyTest {


    @Test
    public void proxy() {
        Waiter target = new NaiveWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(target);
        factory.addAspect(PreGreetingAspect.class);
        Waiter proxy = factory.getProxy();
        System.out.println("---------------------------");
        proxy.greetTo("John");
        System.out.println("---------------------------");
        proxy.serveTo("John");
    }
}
