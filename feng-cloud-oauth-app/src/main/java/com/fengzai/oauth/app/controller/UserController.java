package com.fengzai.oauth.app.controller;

import com.fengzai.oauth.app.POJO.DTO.UserLoginDTO;
import com.fengzai.oauth.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/5/2916:49
 * @Description: 用户action
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserLoginDTO login(String username, String password){
        return userService.login(username, password);
    }


    @GetMapping("/foo")
    @PreAuthorize("hasRole('crmTargetCustomers')")
    public String foo(){
        return "i'm foo";
    }
}
