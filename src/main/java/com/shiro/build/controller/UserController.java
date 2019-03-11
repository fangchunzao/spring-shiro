package com.shiro.build.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FCZ
 * @since 2019/3/1 16:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/userTest")
    public String userTest() {
        return "有User权限";
    }
}
