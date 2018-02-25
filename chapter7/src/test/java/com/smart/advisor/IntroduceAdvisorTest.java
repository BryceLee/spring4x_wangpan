package com.smart.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import com.smart.introduce.ForumService;
import com.smart.introduce.Monitorable;

public class IntroduceAdvisorTest {

    @Test
    public void introduce() {
        String configPath = "com/smart/advisor/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService) ctx.getBean("forumService");
        forumService.removeForum(10);
        Monitorable moniterable = (Monitorable) forumService;
        moniterable.setMonitorActive(true);
        forumService.removeForum(10);
    }
}
