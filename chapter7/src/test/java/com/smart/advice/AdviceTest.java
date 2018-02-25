package com.smart.advice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class AdviceTest {

    @Test
    public void testBeforeAdvice() {
        String configPath = "com/smart/advice/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) ctx.getBean("waiter01");
        waiter.greetTo("John");

        System.out.println("--------------------------");
        waiter = (Waiter) ctx.getBean("waiter02");
        waiter.greetTo("John");
    }

    @Test
    public void testAfterAdvice() {
        String configPath = "com/smart/advice/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) ctx.getBean("waiter03");
        waiter.greetTo("John");
    }

    @Test
    public void testAroundAdvice() {
        String configPath = "com/smart/advice/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) ctx.getBean("waiter04");
        waiter.greetTo("John3");
    }
}
