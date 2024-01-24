package com.paperStar;

import com.paperStar.dao.LoginDao;
import com.paperStar.dao.impl.LoginDaoImpl;
import com.paperStar.pojo.Login;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.LinkedList;

@SpringBootTest
public class LoginDaoTest {
    private LoginDao dao=new LoginDaoImpl();

    @Test
    public void addLogin()throws IOException{
        Login l1 = new Login();
        l1.setName("李四");
        l1.setPassword("1234");
        dao.addUser(l1);
    }

    @Test
    public void findAllUser() throws IOException{
        LinkedList<Login> arr=dao.findAllUser();

        for(int i=0;i<arr.size();i++){
            System.out.println(arr.get(i).toString());
        }
    }

    @Test
    public void findUserByName() throws IOException{

        Login l = dao.findUserByName("sanSZ");

        try{
            System.out.println(l.toString());
        }
        catch(NullPointerException e){
            System.out.println("没有该对象");
           System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteUser() throws IOException{
        //dao.deleteUserByName("张三");
    }

    @Test
    public void addUIdFile() throws IOException{
        File file = new File("UserIdCounter.txt");

        if(!file.exists()){
            try{
                file.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            int uid=1;
            oos.writeObject(uid);
            oos.flush();
            oos.close();
            fos.close();

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
