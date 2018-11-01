package com.wxl.service;

import com.wxl.domain.User;

import java.util.List;

/**
 * 用户服务层入口
 */
public interface UserService {

    /**
     * 保存用户信息
     * @param user
     */
    void saveAccount(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 转账
     * @param sourceName
     * @param descName
     * @param money
     */
    void tranferTo(String sourceName,String descName,float money);
}
