package com.smart.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class RegexpAdvisorTest {

    @Test
    public void regexp() {
        String configPath = "com/smart/advisor/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) ctx.getBean("waiter1");
        waiter.greetTo("John");
        waiter.serveTo("John");
    }
}
