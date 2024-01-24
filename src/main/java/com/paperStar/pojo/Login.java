package com.paperStar.pojo;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * author:
 * date：2023.5.17
 * introduction: 该类主要为登录界面服务，登录界面只需两个主要的属性，name和password。如果使用整个user有些浪费查找时间。
 *               因此新增一个login类来保存用户的name和password信息。
 **/

@Data
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;                    //登录界面用户的名字
    private String password;                //登录界面用户密码
    private int Id;                         //登录界面用户Id
    private boolean status = false;         //用户账号的状态，是否登录

    public Login(){
        super();
    }

    public Login(String name,String passwd,int Id,boolean status){
        super();
        this.name=name;
        this.password=passwd;
        this.Id=Id;
        this.status=status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nname:"+name+"\nid:"+Id+"\npasswd:"+password+"\nstatus:"+status;
    }
}
