package com.wxl.dao;

import com.wxl.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 资源权限管理持久层入口
 */
public interface IPermissionDao {

    /**
     * 根据角色id查询角色可以使用资源
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in(select permissionid from role_permission where roleid = #{roleId}) ")
    List<Permission> findPermissionByRoleId(String roleId) throws Exception;

    /**
     * 查询所有资源
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * 新增资源
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName,jdbcType=VARCHAR},#{url,jdbcType=VARCHAR})")
    void savePermission(Permission permission);

    /**
     * 查询当前角色可以分配的资源路径
     * @param roleid
     * @return
     */
    @Select("select * from permission where id not in(select permissionid from role_permission where roleid =#{roleid})")
    List<Permission> findPermissionAssignRole(String roleid);
}
