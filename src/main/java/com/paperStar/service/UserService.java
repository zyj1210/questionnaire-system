package com.paperStar.service;

import com.paperStar.pojo.User;

import java.io.IOException;

/**
 * author：王瑛菊
 * date：2023.5.21
 * introduction：接口声明
 */


public interface UserService {

    User findUserByName(String name) throws IOException;
    User findUserById(int id) throws IOException;

    boolean addUser(User user) throws IOException;

    boolean modifyUserById(int id,User newUser) throws IOException;

    boolean deleteUserById(int id);
}
