package com.paperStar;

import com.paperStar.dao.LoginDao;
import com.paperStar.dao.UserDao;
import com.paperStar.dao.impl.LoginDaoImpl;
import com.paperStar.dao.impl.UserDaoImpl;
import com.paperStar.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.LinkedList;

/**
 *  author:王瑛菊
 *  date：2023.5.18
 *  introduction：对于文件增删改查的功能测试。
 **/

@SpringBootTest
public class UserDaoTest {

    UserDao dao=new UserDaoImpl();
    LoginDao logindao = new LoginDaoImpl();

    @Test
    public void addUser() throws IOException {
//        User u1=new User(001,"张三","1234",20,1,1);
//        User u2=new User(002,"李四","1234",15,1,2);
//
//        dao.addUser(u1);
//        dao.addUser(u2);
    }

    @Test
    public void findAllUser() throws IOException{
        LinkedList<User> tmp = dao.findAllUser();
        for(int i=0;i<tmp.size();i++){
            System.out.println(tmp.get(i).toString());
        }
    }

    @Test
    public void findUserByNameTest() throws IOException{
        User user = dao.findUserByName("admin");

        try{
            System.out.println(user.toString());
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findUserByIdTest() throws IOException{

        User user = dao.findUserById(1);

        try{
            System.out.println(user.toString());
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void modifyUserById() throws IOException{
//        User user = new User(002,"王五","1234",15,1,2);
//        dao.modifyUserById(002,user);
//
//        User tmp = dao.findUserById(2);
//
//        try{
//            System.out.println(tmp.toString());
//        }
//        catch(NullPointerException e){
//            System.out.println(e.getMessage());
//        }
    }

    @Test
    public void deleteUserById() throws IOException{
//        dao.deleteUserById(002);
//        findAllUser();
//
//        LoginDaoTest test =new LoginDaoTest();
//        test.findAllUser();
    }
}
