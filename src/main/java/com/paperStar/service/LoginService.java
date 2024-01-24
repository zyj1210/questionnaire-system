package com.paperStar.service;

import com.paperStar.pojo.Login;

import java.io.IOException;

/**
 * author：王瑛菊
 * date：2023.5.21
 * introduction：接口声明
 */


public interface LoginService {
    Login findUserByName(String name) throws IOException;

    Login findLoginById(int id) throws IOException;

    boolean deleteLoginByName(String name) throws IOException;

    boolean addLogin(Login login) throws IOException;

    boolean modifyLoginById(int id,Login newLogin) throws IOException;
}
