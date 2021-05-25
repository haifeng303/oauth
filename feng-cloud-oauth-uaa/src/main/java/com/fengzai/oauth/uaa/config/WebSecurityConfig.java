package com.fengzai.oauth.uaa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;


/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/913:50
 * @Description: web security配置
 */
@Configuration
@EnableWebSecurity
@Order(10)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    @Qualifier("userService") //此处必须加上注入  否则会出现UserDetailsService is required错误
    private UserDetailsService userDetailsService;

    @Bean
    @Scope("singleton")
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
//        .passwordEncoder(bCryptPasswordEncoder())
                //security5.0后默认使用DelegatingPasswordEncoder的BCryptPasswordEncoder加密，
                // 所以此处使用明文就用NoOpPasswordEncoder
        .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/oauth/**","/login", "/custom/confirm_access","/test/**", "/health", "/css/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                ;
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/","/oauth/**","/login","/health", "/css/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll();
    }

}
