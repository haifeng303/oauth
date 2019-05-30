package com.example.uaa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/914:18
 * @Description: 接口信息
 */
@RestController
public class controller {

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {

        return "ok";
    }
}
