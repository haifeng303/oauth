package com.fengzai.oauth.app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/5/2917:24
 * @Description: 比较密码
 */
public class BCryptPasswordEncoderUtil {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static boolean matches(String password, String encoderPassword){
        return bCryptPasswordEncoder.matches(password, encoderPassword);
    }
}
