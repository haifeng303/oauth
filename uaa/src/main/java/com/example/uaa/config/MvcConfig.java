//package com.example.uaa.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * @PACKAGE_NAME: com.example.uaa.config
// * @author: rhf
// * @DATE: 2020/4/11
// **/
//@Configuration
//public class MvcConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        //自定义的登陆页面   授权页面
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/oauth/confirm_access").setViewName("oauth_grant");
//    }
//}
