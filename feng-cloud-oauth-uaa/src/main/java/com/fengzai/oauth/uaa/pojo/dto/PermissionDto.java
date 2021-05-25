package com.fengzai.oauth.uaa.pojo.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @PACKAGE_NAME: com.example.uaa.pojo.dto
 * @author: rhf
 * @ProjectName: oauth
 * @description:
 * @DATE: 2021/5/24
 **/
@Data
public class PermissionDto implements GrantedAuthority {
    private String perm;
    @Override
    public String getAuthority() {
        return perm;
    }
}
