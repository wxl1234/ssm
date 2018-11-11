package com.wxl.domain;

import java.io.Serializable;
import java.util.List;

/**
 *  资源权限实体类
 */
public class Permission implements Serializable{
    private String id;
    private String permissionName;
    private String url;

    // 一个资源可以被多个角色共同使用
    private List<Role> role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
