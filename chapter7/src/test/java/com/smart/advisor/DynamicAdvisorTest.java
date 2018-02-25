package com.smart.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class DynamicAdvisorTest {

    @Test
    public void dynamic() {
        String configPath = "com/smart/advisor/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) ctx.getBean("waiter2");
        System.out.println("--------------------------------------");
        waiter.serveTo("Peter");
        System.out.println("--------------------------------------");
        waiter.greetTo("Peter");
        System.out.println("--------------------------------------");
        waiter.serveTo("Peter");
        System.out.println("--------------------------------------");
        waiter.greetTo("John");
    }
}
