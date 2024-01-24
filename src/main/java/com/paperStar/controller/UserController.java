package com.paperStar.controller;

import com.paperStar.pojo.Result;
import com.paperStar.pojo.User;
import com.paperStar.service.UserService;
import com.paperStar.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * author：
 * date：2023.5.26
 * introduction：处理用户个人信息修改界面的请求
 **/

@RestController
public class UserController extends HttpServlet{
    @Autowired
    private UserService userService = new UserServiceImpl();

    /**
     * introduction：根据用户id来获取用户信息
     **/
    @RequestMapping("/user/profile/{uid}")
    public Result modifyPassword(@RequestBody User user,@PathVariable("uid") int id ) throws IOException{

        System.out.println(user.toString());

        User newUser = userService.findUserById(id);

        //判断标志位是否为1，为1返回用户所有信息
        if(user.getGetUserData() == 1){
            return Result.success(newUser);
        }

        return Result.error(7,"获取信息失败");

    }

    /**
     * introduction:设置基本用户信息
     */
    @RequestMapping("/user/setProfile/{uid}")
    public Result setUserData(@RequestBody User user,@PathVariable("uid") int id ) throws IOException{

        System.out.println(user.toString());
        User newUser = userService.findUserById(id);

        //首先判断用户新修改的昵称是否在数据库中出现过
        if(userService.findUserByName(user.getName()) != null && !(newUser.getName().equals(user.getName()))){
            return Result.error(4,"用户名重复，请重试");
        }


        //依次判断某些数据是否传入
        if(!(user.getName().equals("")) && user.getName() != null){
            newUser.setName(user.getName());
        }

        if(user.getSex() != 0){
            newUser.setSex(user.getSex());
        }

        if(user.getMail() != null && !(user.getMail().equals(""))){
            newUser.setMail(user.getMail());
        }

        if(user.getPhoneNum() != null && !(user.getPhoneNum().equals(""))){
            newUser.setPhoneNum(user.getPhoneNum());
        }

        if(user.getSchool() != null && !(user.getSchool().equals(""))) {
            newUser.setSchool(user.getSchool());
        }

        if(user.getGrade() != null && !(user.getGrade().equals(""))){
            newUser.setGrade(user.getGrade());
        }

        if(user.getMajor() != null && !(user.getMajor().equals(""))){
            newUser.setMajor(user.getMajor());
        }

        if(user.getHobby() != null && !(user.getHobby().equals(""))){
            newUser.setHobby(user.getHobby());
        }

        //数据替换完成后，保存修改的数据
        if(userService.modifyUserById(id,newUser)){
            return Result.success();
        }

        return Result.error(5,"修改失败，请重试。");
    }

    /**
     * introduction:设置密码
     */
    @RequestMapping("/user/setPassword/{uid}")
    public Result setPassword(@RequestBody User user,@PathVariable("uid") int id)throws IOException{
        User newUser = userService.findUserById(id);
        //密码修改

        if((user.getOldPassword() != null && user.getNewPassword() != null)){
            if (newUser.getPassword().equals(user.getOldPassword())) {
                newUser.setPassword(user.getNewPassword());
            }
            else{
                return Result.error(3,"原密码错误");
            }
        }

        //数据替换完成后，保存修改的数据
        if(userService.modifyUserById(id,newUser)){
            return Result.success();
        }
        return Result.error(5,"修改失败，请重试。");
    }
}
