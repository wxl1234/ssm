package com.wxl.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 */
public class Role implements Serializable{
    private String id;
    private String roleName;
    private String roleDesc;

    //一个角色可以使用多种资源
    private List<Permission> permissions;
    //一个角色可以对应对个用户
    private List<User> user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
