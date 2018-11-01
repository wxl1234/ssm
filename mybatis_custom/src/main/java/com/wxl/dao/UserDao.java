package com.wxl.dao;

import com.wxl.domain.User;

import java.util.List;

/**
 * 用户逻辑处理入口
 */
public interface UserDao {

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 查询单个用户
     * @param id
     * @return
     */
    User findOne(Integer id);
}
