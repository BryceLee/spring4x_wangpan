package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.smart.domain.Post;
import com.smart.domain.Topic;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Rollback
@Transactional
public class TestBbtForumAnno extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    public BbtForum bbtForum;

    @Test
    public void testBbtForum() throws Throwable {
        System.out.println("begin........");
        Topic topic = new Topic();
        topic.setTopicTitle("Title -pfb");
        Post post = new Post();
        post.setPostText("post content -pfb");
        topic.setPost(post);
        bbtForum.addTopic(topic);
        //    	((BbtForumImpl)bbtForum).setForumDao(null);
        System.out.println("end........");

    }
}
