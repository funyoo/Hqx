package com.funyoo.hqxApp.service;

import com.funyoo.hqxApp.dao.UserDao;
import com.funyoo.hqxApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean updateUser(User user) {
        return userDao.updateUserMess(user);
    }

    public User getUserById(Integer stuId) {
        return userDao.getUserById(stuId);
    }
}
