package com.wxl.service;

import com.wxl.domain.Role;
import com.wxl.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户服务层入口
 */
public interface IUserService extends UserDetailsService{

    /**
     * 查询所有用户信息
     * @return
     */
    List<com.wxl.domain.User> findAll(Integer pageNum,Integer size) throws Exception;

    /**
     * 新增用户信息
     */
    void addUser(User user) throws Exception;


    /**
     * 根据用户id查询用户的信息包含角色信息
     * @param id
     * @return
     */
    User findUserById(String id) throws Exception;


    /**
     * 根据用户id查询用户可以分配的角色
     * @param userid
     * @return
     */
    List<Role> findUserAssignRoles(String userid) throws Exception;

    /**
     * 为当前用户分配新的角色
     * @param userId
     * @param roleId
     */
    void assignRoleToUser(String userId,String roleId) throws Exception;



}
