package com.example.userservice.POJO.EO;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/917:46
 * @Description: 角色
 */
@Data
@Table(name = "t_roles")
@Entity
public class RoleEO {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 组织ID
     */
    private Long organizationId;

    /**
     * 名称
     */
    private String name;

    /**
     * 修改者ID
     */
    private Long modifierId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 类型，0：系统预内置角色，1：普通角色
     */
    private Integer type;

    private static final long serialVersionUID = 1L;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.EAGER)
    @JoinTable(name="t_privilege_role",joinColumns={@JoinColumn(name="role_id",referencedColumnName = "id")},inverseJoinColumns={@JoinColumn(name="privilegedef_id", referencedColumnName = "id")})
    private Set<PermissionEO> permissionEOs;
}
