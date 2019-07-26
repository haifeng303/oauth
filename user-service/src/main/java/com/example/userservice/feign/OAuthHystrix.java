package com.example.userservice.feign;

import com.example.userservice.POJO.DO.JWT;
import org.springframework.stereotype.Component;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/5/2910:31
 * @Description: 被熔断后操作
 */
@Component
public class OAuthHystrix implements OAuthFeign {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }

    @Override
    public void authorize(String authorization, String type, String client, String uri, String state, String scope) {

    }
}
