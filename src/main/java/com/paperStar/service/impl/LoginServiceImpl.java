package com.paperStar.service.impl;

import com.paperStar.dao.LoginDao;
import com.paperStar.dao.impl.LoginDaoImpl;
import com.paperStar.pojo.Login;
import com.paperStar.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * author：王瑛菊
 * date：2023.5.21
 * introduction：LoginService接口实现，主要完成对LoginDao层的封装。
 */


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public Login findUserByName(String name) throws IOException {
        return loginDao.findUserByName(name);
    }

    @Override
    public Login findLoginById(int id) throws IOException {
        return loginDao.findUserById(id);
    }


    @Override
    public boolean addLogin(Login login) throws IOException {

        return loginDao.addUser(login);
    }

    @Override
    public boolean deleteLoginByName(String name) throws IOException {
        return loginDao.deleteUserByName(name);
    }

    @Override
    public boolean modifyLoginById(int id,Login newLogin) throws IOException{
        return loginDao.modifyLoginById(id,newLogin);
    }
}
