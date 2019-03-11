package com.shiro.build.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FCZ
 * @since 2019/3/1 16:22
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/adminTest")
    public String userTest() {
        return "有admin权限";
    }
}
