package com.tower.reback.service;

import com.tower.reback.dao.UserDao;
import com.tower.reback.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByUserAndPassword(String username,String password){
        return userDao.findByUserAndPassword(username,password);
    }

    public int change(User user){
        return userDao.change(user);
    }
}
