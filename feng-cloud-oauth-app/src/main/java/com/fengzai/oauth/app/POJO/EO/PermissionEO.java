package com.fengzai.oauth.app.POJO.EO;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/914:51
 * @Description: 权限相关实体
 */
@Table(name = "t_privilege_def")
@Data
@Entity
public class PermissionEO implements GrantedAuthority {
    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 应用ID
     */
    private Long applicationId;

    /**
     * 权限的名称
     */
    private String name;

    /**
     * 权限的key
     */
    private String key;

    /**
     * 类型,0:菜单,1:操作,2:资源
     */
    private Integer type;

    /**
     * URL
     */
    private String url;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 同级兄弟中的排序
     */
    private Integer sort;

    /**
     * 图标信息,随意填写,由具体的应用自己去处理
     */
    private String icon;

    /**
     * 修改者ID
     */
    private Long modifierId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 是否可见，0：不可见，1：可见
     */
    private Integer inVisual;

    private static final long serialVersionUID = 1L;


    @Override
    public String getAuthority() {
        return this.key;
    }
}
