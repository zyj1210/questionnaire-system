package com.paperStar.dao;

import com.paperStar.pojo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public interface UserDao {
    LinkedList<User> findAllUser() throws IOException;//查询所有的User
    User findUserByName(String name) throws IOException;//通过用户名查找User
    User findUserById(int userId) throws IOException;//通过用户ID查找User

    boolean addUser(User user) throws IOException;//添加用户于当前用户的目录下

    boolean addUserToTable(User user) throws IOException;//添加用户到表中

    boolean modifyUserById(int userId,User newUser) throws IOException;//通过用户id修改用户内容

    boolean deleteUserById(int id) throws IOException;//通过用户id删除用户
}
