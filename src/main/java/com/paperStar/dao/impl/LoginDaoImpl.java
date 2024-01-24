package com.paperStar.dao.impl;

import com.paperStar.dao.LoginDao;
import com.paperStar.dao.UserDao;
import com.paperStar.pojo.Login;
import com.paperStar.pojo.User;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.LinkedList;

/**
 * author:
 * date：2023.5.19
 * introduction: 该类主要为登录界面和注册界面服务，简化了用户信息，以便注册登录时根据用户那成比对密码等。
 **/

@Repository
public class LoginDaoImpl implements LoginDao {

    /**
     * introduction: 查找所有login对象，并将其返回一个链表中。
     **/
    @Override
    public LinkedList<Login> findAllUser() throws IOException {
        File file=new File("LoginData.dat");

        LinkedList<Login> arr =new LinkedList<>();

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //读取
        try{
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fin);
            arr = (LinkedList<Login>) ois.readObject();
            ois.close();
            fin.close();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return arr;
    }

    /**
     * introduction: 通过调用findallUser获取一张保存所有login对象的链表，用名字进行比对，并返回一
     * 个login对象。
     **/
    @Override
    public Login findUserByName(String name) throws IOException{
        LoginDao dao=new LoginDaoImpl();

        LinkedList<Login> arr = dao.findAllUser();

        //查找
        //文件中没有对象，返回空值
        if(arr.isEmpty()){
            System.out.println("文件中没有对象，返回空值");
            return null;
        }
        else {
            for (int i = 0; i < arr.size(); i++) {
                //存在，直接返回
                if (arr.get(i).getName().equals(name)) {
                    Login tmp = arr.get(i);
                    //System.out.println(tmp.toString());
                    return tmp;
                }
            }
        }
        //文件中不存在该对象，返回null
        return null;
    }

    /**
     * introduction: 通过调用findallUser获取一张保存所有login对象的链表，用id进行比对，并返回一
     * 个login对象。
     **/
    @Override
    public Login findUserById(int id) throws IOException{
        LoginDao dao=new LoginDaoImpl();

        LinkedList<Login> arr = dao.findAllUser();

        //查找
        //文件中没有对象，返回空值
        if(arr.isEmpty()){
            System.out.println();
            return null;
        }
        else {
            for (int i = 0; i < arr.size(); i++) {
                //存在，直接返回
                if (arr.get(i).getId() == id) {
                    Login tmp = arr.get(i);
                    return tmp;
                }
            }
        }
        //文件中不存在该对象，返回null
        return null;
    }

    /**
     * introduction: 该方法分为两个主要内容。1、为首次添加第一位用户，此时也并未创建存放用户的文件夹，
     * 因此在判断文件是否创建时，也将第一位用户以linkedlist的方式写入文件中。2、追加用户：此时先读取先
     * 前的数据存放于linkedlist当中，将新用户添加于列表中，而后在写入文件。
     **/
    @Override
    public boolean addUser(Login login) throws IOException{

        File file=new File("LoginData.dat");

        if(!file.exists())
        {
            try {
                file.createNewFile();
                //添加第一位用户时，也即是初次创建该文件。
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    ObjectOutputStream o = new ObjectOutputStream(out);

                    LinkedList<Login> tmp = new LinkedList<>();
                    tmp.add(login);

                    o.writeObject(tmp);
                    o.flush();
                    o.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //为注册的用户添加用户目录，并且写入总表中
                UserDao dao = new UserDaoImpl();
                User tmp = new User(login.getId(),login.getName(),login.getPassword());
                dao.addUser(tmp);

                return true;

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //判断用户名是否重复
        if(findUserByName(login.getName()) != null){
            return false;
        }

        //追加形式写入用户，先读取此前的内容，在链表尾部add后，在进行写入
        LinkedList<Login> ptr = findAllUser();

        File fileID = new File("UserIdCounter.dat");

        if(!fileID.exists()){
            try{
                file.createNewFile();
                try{
                    FileOutputStream fos = new FileOutputStream(fileID);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    int uid=0;
                    oos.writeObject(uid);
                    oos.flush();
                    oos.close();
                    fos.close();

                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        int id = 0;

        //先读后写
        try{
            FileInputStream fis = new FileInputStream(fileID);
            ObjectInputStream ois = new ObjectInputStream(fis);

            id = (int) ois.readObject()+1;
            ois.close();
            fis.close();

            FileOutputStream fos = new FileOutputStream(fileID);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(id);

            oos.flush();
            oos.close();
            fos.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        login.setId(id);

        ptr.add(login);
        try {
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(out);
            o.writeObject(ptr);
            o.flush();
            o.close();
            out.close();
            //System.out.println("true");
        } catch (IOException e) {
            System.out.println("addFalse");
            e.printStackTrace();
            return false;
        }

        //为注册的用户添加用户目录，并且写入总表中，age、sex、role均设为默认
        UserDao dao = new UserDaoImpl();
        User tmp = new User(login.getId(),login.getName(),login.getPassword());
        dao.addUser(tmp);

        return true;
    }

    /**
     * introduction: 通过调用findAllUser获取一张保存所有login对象的链表，用名字进行比对，查找到后
     * 使用链表的remove方法进行删除，而后写回文件。
     **/
    @Override
    public boolean deleteUserByName(String name) throws IOException{
        LoginDao dao=new LoginDaoImpl();

        LinkedList<Login> arr = dao.findAllUser();

        //查找
        if(arr.isEmpty()){
            return true;
        }
        else {
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getName().equals(name)) {
                    arr.remove(i);
                }
            }
        }

        //写回
        File file=new File("LoginData.dat");
        try {
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(out);
            o.writeObject(arr);
            o.flush();
            o.close();
            out.close();
            //System.out.println("true");
        } catch (IOException e) {
            System.out.println("addFalse");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * introduction: 该方法主要用于登陆后修改用户的登录状态，故仅需查找到该用户后修改状态存入文件中
     **/
    @Override
    public boolean modifyLoginById(int id,Login newLogin) throws IOException{
        Login login = findUserById(id);

        if(login == null){
            return false;
        }
        else {
            login.setStatus(newLogin.getStatus());
            LinkedList<Login> arr = findAllUser();

            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getId() == id) {
                    arr.set(i,login);
                }
            }

            File LoginFile=new File("LoginData.dat");

            try{
                FileOutputStream out = new FileOutputStream(LoginFile);
                ObjectOutputStream o = new ObjectOutputStream(out);
                o.writeObject(arr);
                o.flush();
                o.close();
                out.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return true;
    }
}
