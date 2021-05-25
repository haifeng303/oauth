package com.fengzai.oauth.app.service;

import com.fengzai.oauth.app.POJO.DO.JWT;
import com.fengzai.oauth.app.POJO.DTO.UserLoginDTO;
import com.fengzai.oauth.app.POJO.EO.UserEO;
import com.fengzai.oauth.app.feign.OAuthFeign;
import com.fengzai.oauth.app.repository.UserRepository;
import com.fengzai.oauth.app.util.BCryptPasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/5/2915:18
 * @Description: 用户服务
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OAuthFeign oAuthFeign;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }


    @SuppressWarnings("all")
    public UserLoginDTO login(String username, String password){
        UserEO byUsername = userRepository.findByUsername(username);
        if ( byUsername == null)
        {

        }
        if ( !BCryptPasswordEncoderUtil.matches(password, byUsername.getPassword()))
        {

        }
        //Y2xpZW50XzE6MTIzNDU2使用base64解码后 client_1:123456
        JWT jwt =  oAuthFeign.getToken("Basic Y2xpZW50XzE6MTIzNDU2", "password", username, password);
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(byUsername);
        return userLoginDTO;
    }
}
