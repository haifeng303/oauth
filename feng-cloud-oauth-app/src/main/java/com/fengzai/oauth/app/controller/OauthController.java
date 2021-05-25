package com.fengzai.oauth.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/5/3020:06
 * @Description: oauth
 */
@RequestMapping("/oauth")
@Controller
public class OauthController {
    @RequestMapping("/sendRedirect")
    public String getCode(){
        return "forward:";
    }
}
