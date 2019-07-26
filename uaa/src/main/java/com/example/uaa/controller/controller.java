package com.example.uaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/914:18
 * @Description: 接口信息
 */
@Controller
public class controller {

    @RequestMapping(value = "/login.html")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {

        return "login";
    }
}
