package com.wxl.dao;

import com.wxl.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 就是持久层入口
 */
public interface IRoleDao {


    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleid from users_role where userid = #{userId} )")
    @Results({
            @Result(column = "id",property = "permissions",javaType = java.util.List.class,
            many = @Many(select = "com.wxl.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRolesByUserId(String userId) throws Exception;

    /**
     * 查询所有角色信息
     * @return
     */
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    /**
     * 新增角色
     * @param role
     */
    @Insert("insert into role(rolename,roledesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role) throws Exception;

    /**
     * 根据角色id查询就是详细信息
     * @param roleId
     * @return
     */
    @Select("Select * from role where id = #{roldId}")
    Role findRoleByRoleId(String roleId) throws Exception;

    /**
     * 为角分配资源
     * @param permissionid
     * @param roleid
     */
    @Insert("Insert into role_permission(permissionid,roleid) values(#{param1},#{param2})")
    void assignPermissionToRole(String permissionid,String roleid);



}
