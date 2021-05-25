package com.fengzai.oauth.app.POJO.EO;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.*;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/914:24
 * @Description: 用户
 */
@Data
@Table(name = "t_employee")
@Entity
public class UserEO implements UserDetails {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 员工住址地区ID
     */
    private String areaId;

    /**
     * 员工住址详细地址
     */
    private String address;

    /**
     * 所属组织ID
     */
    private Long orgId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 状态,0:普通,1:停用
     */
    private Integer status;

    /**
     * 登录用户名,没有时插入空串
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 业务地区配置,1:限定,2:继承
     */
    private Integer buziareaCfg;

    /**
     * 头像图片路径
     */
    private String avatarPath;

    /**
     * 签名
     */
    private String motto;

    /**
     * 修改人ID
     */
    private Long modifierId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 是否上班,0:下班,1:上班
     */
    private Integer workingState;

    private static final long serialVersionUID = 1L;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="t_employee_role", joinColumns={@javax.persistence.JoinColumn(name="employee_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="role_id")})
    @JoinTable(name="t_employee_role",joinColumns={@JoinColumn(name="employee_id", referencedColumnName = "id")},inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName = "id")})
    private List<RoleEO> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (ObjectUtils.isEmpty(roles))
        {
            return null;
        }
        Set<PermissionEO> permissionEOSet = new HashSet<>();
        for ( RoleEO roleEO: roles)
        {
            permissionEOSet.addAll(roleEO.getPermissionEOs());
        }
        return permissionEOSet;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
