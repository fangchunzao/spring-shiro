package com.shiro.build.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FCZ
 * @since 2019/3/1 14:04
 */
@RestController
@RequestMapping("/api")
public class TestController {

    // 大家都能访问
    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }

    // 验证是否有 USER 权限
    @RequestMapping("/testRoleUser")
    @RequiresRoles(value = {"USER"})
    public String testRoleUser() {
        return "testRoleUser";
    }

    // 验证是否有 ADMIN 权限
    @RequestMapping("/testRoleADMIN")
    @RequiresRoles(value = {"ADMIN"})
    public String testRoleAdmin() {
        return "testRoleAdmin";
    }
}
