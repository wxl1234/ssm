package com.wxl.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxl.dao.IRoleDao;
import com.wxl.dao.IUserDao;
import com.wxl.domain.Role;
import com.wxl.service.IUserService;
import com.wxl.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 用户服务实现类
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;


    @Autowired
   private PasswordEncoder passwordEncoder;
    /**
     * 验证
     * 验证用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            com.wxl.domain.User userInfo = userDao.findUserByUsername(username);
           // 将查询结果封装到框架给定user对象中
            user = new User(
                    userInfo.getUsername(),
                    userInfo.getPassword(),
                    getAuthority(userInfo.getRoles()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 授权
     * 获取指定格式的权限
     * @param roles
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority>  authoritys =new ArrayList<>();
        // 格式化权限名称
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName().toUpperCase()));
        }
        return authoritys;
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<com.wxl.domain.User> findAll(Integer pageNum,Integer size) throws Exception {
        PageHelper.startPage(pageNum,size);
        return userDao.findAll();
    }

    /**
     * 新增用户信息
     * @param user
     */
    @Override
    public void addUser(com.wxl.domain.User user) throws Exception {
        user.setId(UuidUtil.getUuid());
        //对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.SaveUser(user);
        return ;
    }

    /**
     * 根据用户的id查询用户信息
     * @param id
     * @return
     */
    @Override
    public com.wxl.domain.User findUserById(String id) throws Exception {
        com.wxl.domain.User user = userDao.findUserById(id);
        return user;
    }


    /**
     * 查询当前用户可分配的角色信息
     * @param userid
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findUserAssignRoles(String userid) throws Exception {
        return userDao.findUserAssignRoles(userid);
    }

    /**
     * 为当前用户分配新的角色
     * @param userId
     * @param roleId
     * @throws Exception
     */
    @Override
    public void assignRoleToUser(String userId, String roleId) throws Exception {
        userDao.assignRoleToUser(userId,roleId);
    }
}
