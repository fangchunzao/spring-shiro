package com.shiro.build.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FCZ
 * @since 2019/3/1 15:31
 */
@RestController
public class HomeController {

    @GetMapping("/login")
    private String login() {
        //如果已经认证通过，直接跳转到首页
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "index";
        }
        return "login";
    }

    @PostMapping("/login")
    public Object login(String username, String password) {
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {user.login(token);
            // 登录 成功
            return "loginSuccess";
        } catch(Exception e) {
            // 登录 失败
            e.printStackTrace();
        }
        return "loginFail";
    }

    @GetMapping("/getUser")
    public Object getUser() {
        Subject sub = SecurityUtils.getSubject();
        Object obj = sub.getPrincipal();
        return obj;
    }

    @RequestMapping("/unauthorizedRole")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "无权限";
    }

}
