package com.paperStar.controller;

import com.paperStar.pojo.Login;
import com.paperStar.pojo.Result;
import com.paperStar.service.LoginService;
import com.paperStar.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * author：
 * date：2023.5.26
 * introduction：处理登陆界面的请求操作
 **/

@RestController


public class LoginController extends HttpServlet {

    @Autowired
    private LoginService loginService = new LoginServiceImpl();

    /**
     * introduction：处理登陆界面操作，对用户的昵称与密码进行校验
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result Login(@RequestBody Login login) throws IOException {

        String s = login.getName();

        Login tmp = loginService.findUserByName(s);

        //情况一:后台没有该用户数据
        if(tmp == null){
            return Result.error(2,"没有这个对象");
        }
        else{
            //密码正确,允许登录后并将用户登录状态置为true
            if(login.getPassword().equals(tmp.getPassword())){
                tmp.setStatus(true);
                loginService.modifyLoginById(tmp.getId(),tmp);
                return Result.success(tmp.getId());
            }
            //密码错误
            else{
                return Result.error(3,"密码错误，请重试。");
            }
        }
    }

    /**
     * introduction：处理注册界面操作
     */
    @RequestMapping("/register")
    public Result Register(@RequestBody Login login,HttpSession session) throws IOException{
        System.out.println(login.toString());

        //用户昵称不能为空
        if(login.getName() == null || login.getName() == ""){
            return Result.error(5,"用户名不能为空");
        }

        //密码不能为空
        if(login.getPassword() == null || login.getPassword() == ""){
            return Result.error(6,"密码不能为空");
        }

        //前端要求一注册就登录，因此在此处就将帐号状态设置为已登录
        login.setStatus(true);

        //将用户信息添加到后台中
        if(loginService.addLogin(login)){
            String s = login.getName();
            Login tmp = loginService.findUserByName(s);
            System.out.println(tmp.toString());
            return Result.success(0,tmp.getId());
        }
        return Result.error(4,"用户名重复，请重试。");
    }

    /**
     * introduction：处理登出界面操作
     */
    @RequestMapping("/logout")
    public Result Logout(@RequestBody Login login,HttpSession session) throws IOException{

        int id = (int) session.getAttribute("UserId");
        loginService.findLoginById(id).setStatus(false);

        if(loginService.modifyLoginById(id,login)){
            session.invalidate();
            return Result.success();
        }
        return Result.error(0,"退出失败，请重试。");
    }


}
