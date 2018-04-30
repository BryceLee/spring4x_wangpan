package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.dao.ForumDao;
import com.smart.dao.PostDao;
import com.smart.dao.TopicDao;
import com.smart.domain.Forum;
import com.smart.domain.Topic;

@Service
@Transactional
public class BbtForum {

    @Autowired
    public ForumDao forumDao;
    @Autowired
    public TopicDao topicDao;
    @Autowired
    public PostDao postDao;

    public void addTopic(Topic topic) throws Exception {
        topicDao.addTopic(topic);
        //		if(true) throw new PessimisticLockingFailureException("fail");
        postDao.addPost(topic.getPost());
    }


    @Transactional(readOnly = true)
    public Forum getForum(int forumId) {
        return forumDao.getForum(forumId);
    }

    public void updateForum(Forum forum) {
        forumDao.updateForum(forum);
    }

    public int getForumNum() {
        return forumDao.getForumNum();
    }

}
