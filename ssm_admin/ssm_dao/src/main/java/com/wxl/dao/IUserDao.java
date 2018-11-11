package com.wxl.dao;

import com.wxl.domain.Role;
import com.wxl.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 用户持久层入口
 */
public interface IUserDao extends UserDetails{
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Select("select * from users where USERNAME  =#{username}")
    @Results({
            @Result(column = "id",property = "roles",javaType = List.class,
            many = @Many(select = "com.wxl.dao.IRoleDao.findRolesByUserId"))
    })
    User findUserByUsername(String username) throws Exception;


    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select * from users")
    List<User> findAll() throws Exception;

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(column = "id",property = "roles",javaType = List.class,
                    many = @Many(select = "com.wxl.dao.IRoleDao.findRolesByUserId"))
    })
    User findUserById(String id) throws Exception;


    /**
     * 新增用户
     * @param user
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void SaveUser(User user) throws Exception;


    /**
     * 为用户分配角色
     * @param userid
     * @param roleid
     */
    @Insert("insert into users_role(userid,roleid) values(#{param1},#{param2})")
    void assignRoleToUser(String userid,String roleid);

    /**
     * 根据用户id查询当前用户可以分配的角色信息
     * @param userId
     * @return
     */
    @Select("select * from role where id not in(select roleId from users_role where userId = #{userid})")
    List<Role> findUserAssignRoles(String userId);
}
