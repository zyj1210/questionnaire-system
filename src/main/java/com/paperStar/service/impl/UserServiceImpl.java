package com.paperStar.service.impl;

import com.paperStar.dao.UserDao;
import com.paperStar.dao.impl.UserDaoImpl;
import com.paperStar.pojo.User;
import com.paperStar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * author：王瑛菊
 * date：2023.5.21
 * introduction：UserService接口实现，主要完成对UserDao层的封装。
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User findUserByName(String name) throws IOException {
        return userDao.findUserByName(name);
    }

    @Override
    public User findUserById(int id) throws IOException {
        return userDao.findUserById(id);
    }


    /**
     * introduction：controller层新建用户时记得将id置为0，而后在service层根据创建时间赋予id的真正值
     */
    @Override
    public boolean addUser(User user) throws IOException {

        userDao.addUser(user);
        return true;
    }

    @Override
    public boolean modifyUserById(int id,User newUser) throws IOException{
        return userDao.modifyUserById(id,newUser);
    }

    @Override
    public boolean deleteUserById(int id) {
        return deleteUserById(id);
    }
}
