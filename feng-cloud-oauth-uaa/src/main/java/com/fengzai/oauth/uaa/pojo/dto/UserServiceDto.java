package com.fengzai.oauth.uaa.pojo.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @PACKAGE_NAME: com.example.uaa.pojo.dto
 * @author: rhf
 * @ProjectName: oauth
 * @description:
 * @DATE: 2021/5/24
 **/
@Data
public class UserServiceDto implements UserDetails {
    private Long userId;
    private String username;
    private String name;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Integer status;
    private List<Long> roleIdList;
    private List<String> permissions;
    private Date createTime;
    private Long deptId;
    private String deptName;
    private String confirmPassword;
    private String oldPassword;
    private String merchantNo;
    private String merchantUserNo;
    private String newpassword;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = permissions.stream()
                .map(permission ->{
                    PermissionDto permissionDto = new PermissionDto();
                    permissionDto.setPerm(permission);
                    return permissionDto;
                }).collect(Collectors.toSet());
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }
}
