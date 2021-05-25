package com.fengzai.oauth.uaa.service;

import com.fengzai.oauth.uaa.pojo.dto.UserServiceDto;
import com.fengzai.common.exception.BusinessException;
import com.fengzai.common.res.GlobalResponseEnum;
import com.fengzai.common.res.ResultResponse;
import com.fengzai.common.res.SystemCodeEnum;
import com.fengzai.upms.dto.UserInfoDto;
import com.fengzai.upms.feign.MenuFeign;
import com.fengzai.upms.feign.UserFeign;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/918:12
 * @Description: TODO
 */
@Service
public class UserService implements UserDetailsService {

    @Resource
    private MenuFeign menuFeign;
    @Resource
    private UserFeign userFeign;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.buildUserDetails(s);
    }
    private UserServiceDto buildUserDetails(String s){
        ResultResponse<UserInfoDto> edpResponse = userFeign.getUserByUserName(s);
        if (!edpResponse.isSuccess()) {
            throw new BusinessException(SystemCodeEnum.AUTH, GlobalResponseEnum.ILLEGAL_REQUEST,"用户不存在");
        }
        UserInfoDto userInfoDto = edpResponse.getBody();
        ResultResponse<List<String>> listResultResponse = menuFeign.listPermsByUserId(SystemCodeEnum.AUTH, userInfoDto.getUserId());
        UserServiceDto userServiceDto = new UserServiceDto();
        BeanUtils.copyProperties(userInfoDto, userServiceDto);
        userServiceDto.setPermissions(listResultResponse.getBody());
        //此处数据库中密码必须使用对应加密算法后的密文，例如bCryptPasswordEncoder，应该和对应加密方式相匹配
        userServiceDto.setPassword(passwordEncoder.encode("123456"));
        return userServiceDto;
    }
}
