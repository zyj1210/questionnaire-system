package com.paperStar.pojo;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * author:
 * date：2023.5.17
 * introduction: 该类为用户实体类，存放用户的属性及属性的设置、获取方法。引入接口Serializable是为了方便文件读写。
 **/

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int Id;                         //系统自动分配的id
    private String name;                    //用户昵称
    private String password;                //用户密码

    private int sex;                        //用户性别，0为未设置，1为男，2为女
    private String mail;                    //用户邮箱
    private String phoneNum;                //联系电话


    private String school;                  //学校
    private String grade;                   //年级


    private String major;                   //专业
    private String hobby;                   //兴趣

    private String oldPassword;             //旧密码
    private String newPassword;             //新密码


    private int getUserData;                //判断前端是否请求所有信息,0为为请求，1为请求

    public User(){
        super();
    }

    public User(int id,String name,String password){
        super();
        this.Id=id;
        this.name=name;
        this.password=password;

        this.sex = 0;

        this.mail = null;
        this.phoneNum = null;
        this.school = null;
        this.grade = null;
        this.major = null;
        this.hobby = null;

        this.oldPassword = null;
        this.newPassword = null;

        this.getUserData = 0;
    }

    public User(String name,String password,String mail,String phoneNum,String school,String grade,String major,String hobby){
        super();
        this.name=name;
        this.password=password;

        this.mail = mail;
        this.phoneNum = phoneNum;
        this.school = school;
        this.grade = grade;
        this.major = major;
        this.hobby = hobby;
    }

    public int getId(){
        return Id;
    }

    public void setId(int id){
        this.Id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String passwd){
        this.password=passwd;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public int getGetUserData() {
        return getUserData;
    }

    public void setGetUserData(int getUserData) {
        this.getUserData = getUserData;
    }


    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", mail='" + mail + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                ", major='" + major + '\'' +
                ", hobby='" + hobby + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
