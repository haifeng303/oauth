package com.example.uaa.controller;

import com.alibaba.fastjson.JSON;
import com.example.uaa.POJO.EO.UserEO;
import com.example.uaa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/918:30
 * @Description: TODO
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/1")
    public String test1(){
        UserEO user = userRepository.findByUsername("ly");
        String bthrcp1 = bCryptPasswordEncoder.encode("123456");
        String bthrcp2 = bCryptPasswordEncoder.encode("123456");

        System.out.println("123456:BCrypt hash加密:     "+ BCrypt.hashpw("123456",BCrypt.gensalt()));
        System.out.println("123456:BCrypt 1加密:     "+ bthrcp1 + ";比较结果："+bCryptPasswordEncoder.matches("123456", bthrcp1 ));
        System.out.println("123456:BCrypt 2加密:     "+ bthrcp2 + ";比较结果："+bCryptPasswordEncoder.matches("123456", bthrcp2 ));

        System.out.println("结果:" + bCryptPasswordEncoder.matches("123456", bCryptPasswordEncoder.encode("123456")));
        return JSON.toJSONString(user);
    }

    @GetMapping("/2")
    public String test2(){
        System.out.println("salt  1:     "+ BCrypt.gensalt());
        System.out.println("salt  2:     "+ BCrypt.gensalt());
        System.out.println("salt  3:     "+ BCrypt.gensalt());
        System.out.println("salt  4:     "+ BCrypt.gensalt());
        return null;
    }
}
