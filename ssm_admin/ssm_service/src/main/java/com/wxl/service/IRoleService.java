package com.wxl.service;

import com.wxl.domain.Permission;
import com.wxl.domain.Role;

import java.util.List;

/**
 * 角色服务层
 */
public interface IRoleService {

    /**
     * 分页查询所有角色信息
     * @param pageNum
     * @param size
     * @return
     */
    List<Role> findAll(Integer pageNum,Integer size) throws Exception;


    /**
     * 新增角色
     * @param role
     */
    void saveRoel(Role role) throws Exception;


    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    Role findRoleById(String id) throws Exception;


    /**
     * 为当前角色分配资源
     * @param permissionid
     * @param roleid
     */
    void assignPermissionToRole(String permissionid,String roleid );

    /**
     * 查询当前角色可分配的资源
     * @param roleid
     * @return
     */
    List<Permission> findPermissionAssignToRole(String roleid);
}
