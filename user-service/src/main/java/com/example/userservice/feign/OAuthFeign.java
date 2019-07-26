package com.example.userservice.feign;

import com.example.userservice.POJO.DO.JWT;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/5/2910:25
 * @Description: token相关接口
 */
@FeignClient(name = "uaa-service" , fallback = OAuthHystrix.class)
public interface OAuthFeign {
    /**
     * @author rhf30
     * @descript 获取access_token
     * @params [authorization, type, username, password] 
     * @return JWT 
     */ 
    @PostMapping(value = "/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type,
                 @RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * @author rhf30
     * @descript 获取授权码的code
     * @params [authorization, type, username, password] 
     * @return void 
     */
    @PostMapping(value = "/oauth/authorize")
    void authorize(@RequestHeader(value = "Authorization") String authorization, @RequestParam("response_type") String type,
                   @RequestParam("client_id") String client, @RequestParam("redirect_uri") String uri
            , @RequestParam(name = "state", required = false)String state,@RequestParam(name = "scope", required = false)String scope);
    
}
