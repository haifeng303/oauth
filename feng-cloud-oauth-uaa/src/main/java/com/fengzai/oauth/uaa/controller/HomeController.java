package com.fengzai.oauth.uaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/914:18
 * @Description: 接口信息
 */
@Controller
public class HomeController {

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginProcessUrl", "/login");
        return "login";
    }

}
