package com.example.userservice.POJO.DO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/5/2910:49
 * @Description: TODO
 */
@Data
public class JWT implements Serializable {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String jti;
}
