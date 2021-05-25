package com.fengzai.oauth.uaa.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.example.uaa.controller
 * @author: rhf
 * @DATE: 2020/4/11
 **/
@Controller
@SessionAttributes("authorizationRequest")
public class OauthController {
    @RequestMapping("/custom/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("oauth_grant");
        view.addObject("clientId", authorizationRequest.getClientId());
        return view;
    }
}
