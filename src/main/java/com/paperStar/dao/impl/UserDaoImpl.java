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
 * date：2023.5.18
 * introduction: 该类为对用户进行添加、更改、删除、查询等操作。
 **/

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public LinkedList<User> findAllUser() throws IOException{

        File file=new File("userData.dat");

        LinkedList<User> arr =new LinkedList<>();

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try{
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fin);
            arr = (LinkedList<User>) ois.readObject();
            ois.close();
            fin.close();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return arr;
    }

    public User findUserByName(String name) throws IOException{

        LinkedList<User> arr = findAllUser();

        //链表为空直接返回空
        if(arr.isEmpty()){
            return null;
        }
        //通过名字比对查找
        else{
            for (int i = 0; i < arr.size(); i++) {
                //存在，直接返回
                if (arr.get(i).getName().equals(name)) {
                    User usr = arr.get(i);
                    //System.out.println(usr.toString());
                    return usr;
                }
            }
        }
        //没有该用户，返回空
        return null;
    }

    public User findUserById(int id) throws IOException{
        LinkedList<User> arr = findAllUser();

        //链表为空直接返回空
        if(arr.isEmpty()){
            return null;
        }
        //通过名字比对查找
        else{
            for (int i = 0; i < arr.size(); i++) {
                //存在，直接返回
                if (arr.get(i).getId() == id) {
                    return arr.get(i);
                }
            }
        }
        //没有该用户，返回空
        return null;
    }

    /**
     * introduction: 依据文件系统的结构，在User目录下创建一个userid的文件夹，并且把个人信息写入文件中进行保存。
     * 注意：路径名需要根据服务器的名字进行更改！
     **/
    @Override
    public boolean addUser(User user) throws IOException{

        String str = File.separator;
        //linux路径
        File file = new File(str+"paperStar"+str+"paperStar"+str+"users"+str+"u"+user.getId()+str+user.getId()+".dat");

        //window路径
        //File file = new File("D:\\软件开发综合实训\\开发\\paperStar\\users\\u"+user.getId()+"\\"+user.getId()+".dat");

        if(!file.exists()){
            //linux路径
            createFile(str+"paperStar"+str+"paperStar"+str+"users"+str+"u"+user.getId()+str,user.getId()+".dat");

            //window路径
            //createFile("D:\\软件开发综合实训\\开发\\paperStar\\users\\u"+user.getId()+"\\",user.getId()+".dat");
        }

        baseFileSaveUser(user,file);

        addUserToTable(user);

        return true;
    }

    /**
     * introduction: 将user存于一个总用户信息表中，以便查找修改。
     **/
    @Override
    public boolean addUserToTable(User user) throws IOException{
        File file=new File("userData.dat");

        if(!file.exists()){
            try {
                file.createNewFile();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    ObjectOutputStream o = new ObjectOutputStream(out);

                    LinkedList<User> tmp = new LinkedList<>();
                    tmp.add(user);

                    o.writeObject(tmp);
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

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        LinkedList<User> ptr = findAllUser();
        ptr.add(user);
        baseFileSaveList(ptr,file);
        return true;
    }

    @Override
    public boolean modifyUserById(int userId,User newUser) throws IOException{

        if(findUserByName(newUser.getName()) != null && findUserById(userId).getName() == newUser.getName()){
            return false;
        }

        User user = findUserById(userId);

        if(user == null){
            System.out.println("没有这个用户");
            return false;
        }
        else{
            //修改总的表中的内容
            LinkedList<User> arr = findAllUser();
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getId() == userId) {
                    arr.set(i,newUser);
                }
            }

            //写入总的表中
            File fileTable = new File("userData.dat");
            baseFileSaveList(arr,fileTable);

            //写入用户目录中

            String str = File.separator;

            //window路径
            //File file = new File("D:\\软件开发综合实训\\开发\\paperStar\\users\\u"+user.getId()+"\\"+user.getId()+".dat");

            //linux路径
            File file = new File(str+"paperStar"+str+"paperStar"+str+"users"+str+"u"+user.getId()+str+user.getId()+".dat");

            baseFileSaveUser(newUser,file);

            //修改loginData中的内容
            Login newLogin = new Login(newUser.getName(),newUser.getPassword(),newUser.getId(),true);
            LoginDao dao = new LoginDaoImpl();

            //Login old = dao.findUserByName(user.getName());
            LinkedList<Login> ptr = dao.findAllUser();

            for (int i = 0; i < ptr.size(); i++) {
                if (ptr.get(i).getId() == userId) {
                    ptr.set(i,newLogin);
                }
            }

            File LoginFile=new File("LoginData.dat");

            try{
                FileOutputStream out = new FileOutputStream(LoginFile);
                ObjectOutputStream o = new ObjectOutputStream(out);
                o.writeObject(ptr);
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

    /**
     * introduction:删除某一用户
     **/
    @Override
    public boolean deleteUserById(int id) throws IOException{
        //删除总表中的内容
        LinkedList<User> arr = findAllUser();
        User user = findUserById(id);

        for(int i=0;i<arr.size();i++){
            if(arr.get(i).getId()==id){
                arr.remove(i);
            }
        }

        baseFileSaveList(arr,new File("userData.dat"));

        //删除用户目录的内容
        String path = "D:\\软件开发综合实训\\开发\\paperStar\\User\\"+String.valueOf(id);
        deleteDirectory(path);

        //删除LoginData里的内容
        LoginDao dao = new LoginDaoImpl();
        dao.deleteUserByName(user.getName());
        return false;
    }

    //完成LinkedList的写入操作
    public boolean baseFileSaveList(LinkedList<User> list,File file) throws IOException{
        try{
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(out);
            o.writeObject(list);
            o.flush();
            o.close();
            out.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    //完成用户目录的写入操作
    public boolean baseFileSaveUser(User user,File file) throws IOException{
        try{
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(out);
            o.writeObject(user);
            o.flush();
            o.close();
            out.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     */
    public boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }


    public void createFile(String filePath, String fileName) {
        File folder = new File(filePath);
        //文件夹路径不存在
        if (!folder.exists() && !folder.isDirectory()) {
            System.out.println("文件夹路径不存在，创建路径:" + filePath);
            folder.mkdirs();
        } else {
            System.out.println("文件夹路径存在:" + filePath);
        }

        // 如果文件不存在就创建
        File file = new File(filePath + fileName);
        if (!file.exists()) {
            System.out.println("文件不存在，创建文件:" + filePath + fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件已存在，文件为:" + filePath + fileName);
        }
    }

}
