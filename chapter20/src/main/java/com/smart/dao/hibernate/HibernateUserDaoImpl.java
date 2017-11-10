package com.smart.dao.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smart.dao.UserDao;
import com.smart.domain.User;

@Repository("hibernateUserDao")
public class HibernateUserDaoImpl implements UserDao {


    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Override
    public User findUserByUserName(String userName) {
        String hql = " from User u where u.userName=?";
        List<User> users = (List<User>) this.getHibernateTemplate().find(hql, userName);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int getMatchCount(String userName, String password) {
        return 0;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void updateLoginInfo(User user) {}

    @Override
    public void clean() {

    }
}
