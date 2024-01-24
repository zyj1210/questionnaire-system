package com.paperStar.dao;

import com.paperStar.pojo.Login;
import com.paperStar.pojo.User;

import java.io.IOException;
import java.util.LinkedList;

public interface LoginDao {
    LinkedList<Login> findAllUser() throws IOException;

    Login findUserByName(String name) throws IOException;

    Login findUserById(int id) throws IOException;

    boolean addUser(Login login) throws IOException;

    boolean deleteUserByName(String name) throws IOException;

    boolean modifyLoginById(int id,Login newLogin) throws IOException;
}
